package com.chichaykin.recipes.ui.detail

import com.chichaykin.recipes.ui.MvpView

interface DetailView : MvpView {

    fun show(ingredients: List<String>)

    fun showError(ingredients: String)
}
