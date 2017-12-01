package com.example.rahulkumar.photoapp.Models.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyPhotos {

    @SerializedName("current_page")
    @Expose
    var currentPage: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("total_items")
    @Expose
    var totalItems: Int? = null
    @SerializedName("photos")
    @Expose
    var photos: List<Photo>? = null

}