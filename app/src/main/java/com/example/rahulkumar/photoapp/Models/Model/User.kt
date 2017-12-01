package com.example.rahulkumar.photoapp.Models.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("firstname")
    @Expose
    var firstname: String? = null
    @SerializedName("lastname")
    @Expose
    var lastname: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("usertype")
    @Expose
    var usertype: Int? = null
    @SerializedName("fullname")
    @Expose
    var fullname: String? = null
    @SerializedName("userpic_url")
    @Expose
    var userpicUrl: String? = null
    @SerializedName("userpic_https_url")
    @Expose
    var userpicHttpsUrl: String? = null
    @SerializedName("cover_url")
    @Expose
    var coverUrl: String? = null
    @SerializedName("upgrade_status")
    @Expose
    var upgradeStatus: Int? = null
    @SerializedName("store_on")
    @Expose
    var storeOn: Boolean? = null
    @SerializedName("affection")
    @Expose
    var affection: Int? = null
    @SerializedName("avatars")
    @Expose
    var avatars: Avatars? = null

}