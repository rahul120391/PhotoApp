package com.example.rahulkumar.photoapp.Models.Network

/**
 * Created by rahulkumar on 01/12/17.
 */
object RetrofitServiceProvider {

    private var retrofitServiceAnnotator:RetrofitServiceAnnotator?=null

    val serviceProvider:RetrofitServiceAnnotator?
        get() {
            if(retrofitServiceAnnotator==null){
                init()
            }
            return retrofitServiceAnnotator
        }

    fun init(){
        retrofitServiceAnnotator=RetrofitAdapter.retrofitInstance?.create(RetrofitServiceAnnotator::class.java)
    }


}