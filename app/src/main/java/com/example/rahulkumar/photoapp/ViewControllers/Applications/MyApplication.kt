package com.example.rahulkumar.mvvmarchitecture.View

import android.app.Application
import com.example.rahulkumar.photoapp.Models.Network.RetrofitAdapter
import com.example.rahulkumar.photoapp.Models.Network.RetrofitServiceProvider

/**
 * Created by rahulkumar on 05/11/17.
 */
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        SingletonContext.init(this)
        RetrofitAdapter.intializeRetrofitAdapter()
        RetrofitServiceProvider.init()
    }
}