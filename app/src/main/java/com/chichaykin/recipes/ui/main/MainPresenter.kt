package com.chichaykin.recipes.ui.main

import com.chichaykin.recipes.network.Recipe
import com.chichaykin.recipes.network.RecipeApi
import com.chichaykin.recipes.ui.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(val recipeApi: RecipeApi) : Presenter<MainView>() {

    fun subscribeToData(text: String) {
        var subscription = recipeApi.search(text, 1)
                .subscribeOn(Schedulers.io())
                .map { response -> response.list }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fillData, this::showError)

        compositeDisposable.addAll(subscription)
    }

    private fun showError(error: Throwable) {
        view?.run {
            show(error)
        }
    }


    private fun fillData(list: List<Recipe>?) {
        view?.run {
            show(list)
        }
    }
}

