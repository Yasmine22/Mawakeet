package com.yasmineraafat.mawakeet.remote

import com.yasmineraafat.googlebooks.remote.Urls
import com.yasmineraafat.mawakeet.models.MawakeetResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {
    @GET(Urls.GET_MAWAKEET)
    fun GET_MAWAKEET(@Path("year") year:String,@Path("month") month:String,
                     @Query("longitude") longitude:Double,@Query("latitude") latitude:Double):
            Observable<MawakeetResponse>
}