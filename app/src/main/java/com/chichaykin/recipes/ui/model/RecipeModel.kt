package com.chichaykin.recipes.ui.model

import android.os.Parcel
import android.os.Parcelable

data class RecipeModel(val id: String,
                       val imageUrl: String,
                       val title: String,
                       val ingredients: List<String>,
                       val publisher: String,
                       val socialRank: Double,
                       val sourceUrl: String,
                       val recipeUrl: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.createStringArrayList(),
            source.readString(),
            source.readDouble(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(imageUrl)
        writeString(title)
        writeStringList(ingredients)
        writeString(publisher)
        writeDouble(socialRank)
        writeString(sourceUrl)
        writeString(recipeUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecipeModel> = object : Parcelable.Creator<RecipeModel> {
            override fun createFromParcel(source: Parcel): RecipeModel = RecipeModel(source)
            override fun newArray(size: Int): Array<RecipeModel?> = arrayOfNulls(size)
        }
    }
}