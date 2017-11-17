package com.chichaykin.recipes.network

import com.google.gson.annotations.SerializedName

import java.util.Collections

class SearchResponse {
    @SerializedName("count")
    internal var count: Int = 0
    @SerializedName("recipes")
    internal var list = emptyList<Recipe>()
}
