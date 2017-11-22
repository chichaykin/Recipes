package com.chichaykin.recipes.network

import com.google.gson.annotations.SerializedName

class Recipe {
    @SerializedName("recipe_id")
    var id: String = ""

    @SerializedName("image_url")
    var imageUrl: String = ""

    @SerializedName("title")
    var title: String = ""

    @SerializedName("ingredients")
    var ingredients: List<String> = emptyList()

    @SerializedName("publisher")
    var publisher: String = ""

    @SerializedName("social_rank")
    var socialRank: Double = 0.0

    @SerializedName("source_url")
    var sourceUrl: String = ""

    @SerializedName("f2f_url")
    var recipeUrl: String = ""

}
