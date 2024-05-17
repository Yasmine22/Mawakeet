package com.yasmineraafat.mawakeet.repos


import com.yasmineraafat.mawakeet.models.MawakeetResponse
import com.yasmineraafat.mawakeet.remote.RetrofitApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MawakeetRepository @Inject constructor(private val retrofitApi: RetrofitApi) {

    fun getMawakeet(
        year: String, month: String,
        longitude: Double, latitude: Double
    ): Observable<MawakeetResponse> {
        return retrofitApi.GET_MAWAKEET(year, month, longitude, latitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}