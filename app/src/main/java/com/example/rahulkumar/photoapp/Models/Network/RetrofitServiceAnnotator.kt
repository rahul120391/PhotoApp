package com.example.rahulkumar.photoapp.Models.Network


import com.example.rahulkumar.photoapp.Models.Model.MyPhotos
import com.example.rahulkumar.photoapp.Models.Util.Constants
import com.example.rahulkumar.photoapp.Models.Util.URLS
import io.reactivex.Flowable

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rahulkumar on 18/11/17.
 */

public interface RetrofitServiceAnnotator {

    @GET(URLS.FETCH_PHOTOS)
    fun fetchPhotos(@Query(Constants.OAUTH_CONSUMER_KEY) key:String): Flowable<MyPhotos>

}
