package com.example.rahulkumar.photoapp.Models.Interactor

/**
 * Created by rahulkumar on 01/12/17.
 */
interface ModelToViewDataTransfer {

    fun Success(t:Any)
    fun Failure(message:String?)
}