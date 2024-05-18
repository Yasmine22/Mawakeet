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
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class MawakeetViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var repository: MawakeetRepository
    val date = MutableLiveData<Date>()
    val timings = MutableLiveData<Timings>()
    val errorMessage = MutableLiveData<String>()
    private val context: Context
        get() = getApplication<Application>().applicationContext
    private var appDatabase = ApplicationDatabase.getInstance(context)!!

    fun getMawakeet() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR).toString()
        val month = calendar.get(Calendar.MONTH).plus(1).toString()
//        val month = "06"
        val longitude = 31.38333
        val latitude = 31.05
        repository.getMawakeet(year, month, longitude, latitude).subscribe(object :
            Observer<MawakeetResponse> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(newsAPIResponse: MawakeetResponse) {
                val timingList: MutableList<Timings> = ArrayList<Timings>().toMutableList()
                for (item in newsAPIResponse.data) {
                    appDatabase.showDatesDao().insertDate(item.date).subscribeOn(
                        Schedulers.io()
                    )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            //success
                            Log.e("date id", it.toString())
                            item.timings.dateId = it
                            Log.e("timing object",item.timings.toString())
                            timingList += item.timings

                        }, { exception: Throwable ->

                            exception.message?.let { Log.e("DB Date", it) }

                        }).let {
                            it.let { compositeDisposable.add(it) }

                        }
                }

                insertTimings(timingList)
            }

            override fun onError(e: Throwable) {

                errorMessage.value = e.message
            }

            override fun onComplete() {
            }
        })
    }

    private fun insertTimings(timings: List<Timings>) {
        Log.e("insert timing", timings.toString())
        appDatabase.showTimingsDao().insertTimingsArray(timings).subscribeOn(
            Schedulers.io()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //success
                Log.e("timing", it.toString())
                getDateFromDBByCurrentDate()


            }, { exception: Throwable ->
                exception.message?.let { Log.e("DB Timing", it) }

            }).let {
                it.let { compositeDisposable.add(it) }

            }
    }

    public fun getDateFromDBByCurrentDate() {

        Log.e("Today", getCurrentDate())
        appDatabase.showDatesDao().getSelectedDateByTimestamp(getCurrentDate())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //success
                date.value = it
                getTimingByDateId(it.id)
                Log.e("timestamp success", it.toString())

            }, { exception: Throwable ->
                deleteAllDataFromDB()
                Log.e("timestamp error", exception.toString())

            }).let {
                it?.let { compositeDisposable.add(it) }

            }
    }

    private fun deleteAllDataFromDB() {
        appDatabase.showDatesDao().deleteDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                Log.e("date deleted", "$it")

                getMawakeet()

            }, { exception: Throwable ->
                Log.e("data deleted", "${exception.message}")


            }).let {
                it.let { compositeDisposable.add(it) }

            }
    }


    private fun getTimingByDateId(id: Long) {

        appDatabase.showTimingsDao().getSelectedTimingByDate(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //success
                timings.value = it

            }, { exception: Throwable ->
                exception.message?.let { Log.e("DB Date", it) }

            }).let {


            }

    }

    private fun getCurrentDate(): String {
        val time = Calendar.getInstance().time
        return SimpleDateFormat("dd MMMM yyyy").format(time)

    }

}