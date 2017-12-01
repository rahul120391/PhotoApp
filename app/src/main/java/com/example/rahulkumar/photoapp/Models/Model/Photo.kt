package com.example.rahulkumar.photoapp.Models.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: Any? = null
    @SerializedName("camera")
    @Expose
    var camera: String? = null
    @SerializedName("lens")
    @Expose
    var lens: String? = null
    @SerializedName("focal_length")
    @Expose
    var focalLength: String? = null
    @SerializedName("iso")
    @Expose
    var iso: String? = null
    @SerializedName("shutter_speed")
    @Expose
    var shutterSpeed: String? = null
    @SerializedName("aperture")
    @Expose
    var aperture: String? = null
    @SerializedName("times_viewed")
    @Expose
    var timesViewed: Int? = null
    @SerializedName("rating")
    @Expose
    var rating: Double? = null
    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("category")
    @Expose
    var category: Int? = null
    @SerializedName("location")
    @Expose
    var location: Any? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Any? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Any? = null
    @SerializedName("taken_at")
    @Expose
    var takenAt: Any? = null
    @SerializedName("hi_res_uploaded")
    @Expose
    var hiResUploaded: Int? = null
    @SerializedName("for_sale")
    @Expose
    var forSale: Boolean? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("votes_count")
    @Expose
    var votesCount: Int? = null
    @SerializedName("favorites_count")
    @Expose
    var favoritesCount: Int? = null
    @SerializedName("comments_count")
    @Expose
    var commentsCount: Int? = null
    @SerializedName("nsfw")
    @Expose
    var nsfw: Boolean? = null
    @SerializedName("sales_count")
    @Expose
    var salesCount: Int? = null
    @SerializedName("for_sale_date")
    @Expose
    var forSaleDate: Any? = null
    @SerializedName("highest_rating")
    @Expose
    var highestRating: Double? = null
    @SerializedName("highest_rating_date")
    @Expose
    var highestRatingDate: String? = null
    @SerializedName("license_type")
    @Expose
    var licenseType: Int? = null
    @SerializedName("converted")
    @Expose
    var converted: Int? = null
    @SerializedName("collections_count")
    @Expose
    var collectionsCount: Int? = null
    @SerializedName("crop_version")
    @Expose
    var cropVersion: Int? = null
    @SerializedName("privacy")
    @Expose
    var privacy: Boolean? = null
    @SerializedName("profile")
    @Expose
    var profile: Boolean? = null
    @SerializedName("for_critique")
    @Expose
    var forCritique: Boolean? = null
    @SerializedName("critiques_callout_dismissed")
    @Expose
    var critiquesCalloutDismissed: Boolean? = null
    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null
    @SerializedName("images")
    @Expose
    var images: List<Image>? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("positive_votes_count")
    @Expose
    var positiveVotesCount: Int? = null
    @SerializedName("converted_bits")
    @Expose
    var convertedBits: Int? = null
    @SerializedName("watermark")
    @Expose
    var watermark: Boolean? = null
    @SerializedName("image_format")
    @Expose
    var imageFormat: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("licensing_requested")
    @Expose
    var licensingRequested: Boolean? = null
    @SerializedName("licensing_suggested")
    @Expose
    var licensingSuggested: Boolean? = null
    @SerializedName("is_free_photo")
    @Expose
    var isFreePhoto: Boolean? = null

}