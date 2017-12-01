package com.example.rahulkumar.photoapp.Models.Interactor

import com.example.rahulkumar.photoapp.Models.Model.MyPhotos

/**
 * Created by rahulkumar on 01/12/17.
 */
interface IAPIView {
    fun login(email:String,password:String)
    fun clearDisposables()
    fun fetchPhotos()
    fun moveResultFromIoToUi(myPhotos: MyPhotos)
    fun moveErrorToUiThread(e:Throwable)

}