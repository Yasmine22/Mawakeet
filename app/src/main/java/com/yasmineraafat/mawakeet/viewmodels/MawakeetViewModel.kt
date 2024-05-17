package com.yasmineraafat.mawakeet.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yasmineraafat.mawakeet.database.ApplicationDatabase
import com.yasmineraafat.mawakeet.models.Date
import com.yasmineraafat.mawakeet.models.MawakeetResponse
import com.yasmineraafat.mawakeet.models.Timings
import com.yasmineraafat.mawakeet.repos.MawakeetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MawakeetViewModel @Inject constructor(application: Application) :  AndroidViewModel(application) {
    @Inject lateinit var repository: MawakeetRepository
    val date = MutableLiveData<Date>()
    val timings = MutableLiveData<Timings>()
    val errorMessage = MutableLiveData<String>()
    private val context: Context
        get() = getApplication<Application>().applicationContext
    private var appDatabase = ApplicationDatabase.getInstance(context)!!

    fun getMawakeet(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR).toString()
        val month = calendar.get(Calendar.MONTH).plus(1).toString()
        val longitude = 31.38333
        val latitude =31.05
       val result = repository.getMawakeet(year, month, longitude, latitude).subscribe(object :
           Observer<MawakeetResponse> {
           override fun onSubscribe(d: Disposable) {
           }

           override fun onNext(newsAPIResponse: MawakeetResponse) {

               for (item in newsAPIResponse.data){
                   appDatabase?.showDatesDao()?.insertDate(item.date)?.subscribeOn(
                       Schedulers.io())
                       ?.observeOn(AndroidSchedulers.mainThread())
                       ?.subscribe({
                           //success
                           Log.e("date", it.toString())
                           item.timings.dateId = it
                           insertTimings(item.timings)

                       }, { exception:Throwable ->

                           exception.message?.let { Log.e("DB Date", it) }

                       }).let {

                       }
               }

               getDateByTimestamp()
           }

           override fun onError(e: Throwable) {

               errorMessage.value = e.message
           }

           override fun onComplete() {
           }
    })
    }

    private fun insertTimings(timings: Timings) {
        appDatabase?.showTimingsDao()?.insertTimings(timings)?.subscribeOn(
            Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                //success
                Log.e("timing", it.toString())

            }, { exception:Throwable ->
                exception.message?.let { Log.e("DB Timing", it) }

            }).let {

            }
    }

    public fun getDateByTimestamp(){

        appDatabase.showDatesDao().getSelectedDateByTimestamp(getCurrentDate())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //success
                date.value = it
                getTimingByDateId(it.id)
                       Log.e("timestamp success",it.toString())

            }, { exception:Throwable ->
                deleteAllDataFromDB()
                getMawakeet()
                Log.e("timestamp error",exception.toString())

            }).let {

            }
    }

    private fun deleteAllDataFromDB() {
        appDatabase.showDatesDao().deleteDate()
        appDatabase.showTimingsDao().deleteTimings()
    }

    private fun getTimingByDateId(id: Long) {

        appDatabase.showTimingsDao().getSelectedTimingByDate(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //success
               timings.value = it

            }, { exception:Throwable ->
                exception.message?.let { Log.e("DB Date", it) }

            }).let {


            }

    }

    private fun getCurrentDate(): String {
        val time = Calendar.getInstance().time
        return SimpleDateFormat("dd MMMM yyyy").format(time)

    }

}