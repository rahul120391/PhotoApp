package com.example.rahulkumar.photoapp.Models.Network

import com.example.rahulkumar.photoapp.Models.Util.URLS
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rahulkumar on 18/11/17.
 */

object RetrofitAdapter {

    private var retrofit: Retrofit? = null

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                intializeRetrofitAdapter()
            }
            return retrofit
        }

    fun intializeRetrofitAdapter() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client1 = OkHttpClient.Builder().addInterceptor(interceptor)
        retrofit = Retrofit.Builder()
                .baseUrl(URLS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client1.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


}
