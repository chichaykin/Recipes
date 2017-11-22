package com.chichaykin.recipes.ui.detail

import android.util.Log
import com.chichaykin.recipes.network.RecipeApi
import com.chichaykin.recipes.ui.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val TAG: String = "DetailPresenter"

class DetailPresenter(private val recipeApi: RecipeApi) : Presenter<DetailView>() {

    fun subscribeToIngredients(id: String) {
        compositeDisposable.clear()

        val subscription = recipeApi.get(id)
                .subscribeOn(Schedulers.io())
                .map { response -> response.recipe?.ingredients ?: ArrayList() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fillData, this::showError)

        compositeDisposable.addAll(subscription)
    }

    private fun showError(error: Throwable) {
        Log.e(TAG, "showError: ", error)
        view?.run {
            showError(error.toString())
        }
    }

    private fun fillData(ingredients: List<String>) {
        Log.d(TAG, "Ingredients: " + ingredients.size)
        view?.run {
            show(ingredients)
        }
    }
}
