package com.chichaykin.recipes.ui.main

import android.util.Log
import com.chichaykin.recipes.network.Recipe
import com.chichaykin.recipes.network.RecipeApi
import com.chichaykin.recipes.ui.Presenter
import com.chichaykin.recipes.ui.model.RecipeModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

const val TAG = "MainPresenter"

class MainPresenter(private val recipeApi: RecipeApi) : Presenter<MainView>() {

    private var savedList: List<Recipe> = emptyList()

    private fun subscribeToData(text: String) {
        Log.d(TAG, "Start query: " + text)
        compositeDisposable.clear()

        val subscription = recipeApi.search(text)
                .subscribeOn(Schedulers.io())
                .map { response -> response.list }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fillData, this::showError)

        compositeDisposable.addAll(subscription)
    }

    private fun showError(error: Throwable) {
        Log.e(TAG, "Getting error: " + error)
        savedList = emptyList()
        view?.run {
            showErrorMessage(error.message!!)
        }
    }

    private fun fillData(list: List<Recipe>) {
        Log.d(TAG, "Getting list: " + list.size)
        savedList = list
        view?.run {
            show(list)
        }
    }

    private fun convertToModel(item: Recipe): RecipeModel {
        return RecipeModel(
                id = item.id,
                imageUrl = item.imageUrl,
                title = item.title,
                ingredients = ArrayList(item.ingredients),
                publisher = item.publisher,
                socialRank = item.socialRank,
                sourceUrl = item.sourceUrl,
                recipeUrl = item.recipeUrl
        )
    }

    override fun attachView(view: MainView) {
        super.attachView(view)

        //refill data on rotation
        if (!savedList.isEmpty()) {
            view.show(savedList)
        }
    }

    fun search(text: String) {
        view?.run {
            showProgress()
            subscribeToData(text)
        }
    }

    fun onClick(item: Recipe) {
        view?.run {
            showDetail(convertToModel(item))
        }
    }
}

