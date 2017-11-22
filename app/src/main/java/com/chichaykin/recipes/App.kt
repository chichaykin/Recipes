package com.chichaykin.recipes

import android.app.Application
import com.chichaykin.recipes.network.NetworkModule
import com.chichaykin.recipes.ui.detail.DetailPresenter
import com.chichaykin.recipes.ui.main.MainPresenter

class App : Application() {

    lateinit var mainPresenter: MainPresenter
    lateinit var detailPresenter: DetailPresenter

    override fun onCreate() {
        super.onCreate()

        val api = NetworkModule().buildNetworkApi()
        mainPresenter = MainPresenter(api)
        detailPresenter = DetailPresenter(api)
    }


}