package com.chichaykin.recipes.ui.main


import com.chichaykin.recipes.network.Recipe
import com.chichaykin.recipes.ui.MvpView

interface MainView : MvpView {

    fun show(error: Throwable)

    fun show(list: List<Recipe>?)
}
