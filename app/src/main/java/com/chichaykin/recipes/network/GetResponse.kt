package com.chichaykin.recipes.network

import com.google.gson.annotations.SerializedName

internal class GetResponse {
    @SerializedName("recipe")
    var recipe: Recipe? = null
}
