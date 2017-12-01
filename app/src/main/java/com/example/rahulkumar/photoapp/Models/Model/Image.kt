package com.example.rahulkumar.photoapp.Models.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Image {

    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("https_url")
    @Expose
    var httpsUrl: String? = null
    @SerializedName("format")
    @Expose
    var format: String? = null

}