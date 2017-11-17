package com.chichaykin.recipes.network

import com.google.gson.annotations.SerializedName

class Recipe {
    @SerializedName("image_url")
    var imageUrl: String? = null
    @SerializedName("title")
    var title: String? = null
}
