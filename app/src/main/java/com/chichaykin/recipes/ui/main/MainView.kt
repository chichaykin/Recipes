package com.chichaykin.recipes.ui.main


import com.chichaykin.recipes.network.Recipe
import com.chichaykin.recipes.ui.MvpView
import com.chichaykin.recipes.ui.model.RecipeModel

interface MainView : MvpView {

    fun showErrorMessage(error: String)

    fun show(list: List<Recipe>)

    fun emptyItems()

    fun showProgress()

    fun showDetail(model: RecipeModel)
}
