package com.chichaykin.recipes.network

import com.google.gson.annotations.SerializedName

class SearchResponse {

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("recipes")
    var list = emptyList<Recipe>()
}
