package com.yasmineraafat.googlebooks.remote

import com.google.gson.GsonBuilder
import com.yasmineraafat.googlebooks.remote.Urls
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {
        fun getClient(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build()

            val gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        }
    }
}