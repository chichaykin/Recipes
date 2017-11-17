package com.chichaykin.recipes.ui

import android.support.v7.app.AppCompatActivity


abstract class MvpActivity<V : MvpView, out P : Presenter<V>> : AppCompatActivity(), MvpView {

    protected abstract val presenter: P

    override fun onResume() {
        super.onResume()
        presenter.attachView(this as V)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }
}
