package com.example.rahulkumar.photoapp.Models.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Avatars {

    @SerializedName("default")
    @Expose
    var default: Default? = null
    @SerializedName("large")
    @Expose
    var large: Large? = null
    @SerializedName("small")
    @Expose
    var small: Small? = null
    @SerializedName("tiny")
    @Expose
    var tiny: Tiny? = null

}