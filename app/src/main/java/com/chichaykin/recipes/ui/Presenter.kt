package com.chichaykin.recipes.ui

import io.reactivex.disposables.CompositeDisposable

open class Presenter<V : MvpView> {
    val compositeDisposable = CompositeDisposable()
    var view: V? = null

    open fun attachView(view: V) {
        this.view = view
    }
    fun detachView() {
        compositeDisposable.clear()
        view = null
    }
}