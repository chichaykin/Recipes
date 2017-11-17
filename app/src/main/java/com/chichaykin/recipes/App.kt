package com.chichaykin.recipes

import android.app.Application
import com.chichaykin.recipes.network.NetworkModule
import com.chichaykin.recipes.ui.main.MainPresenter

class App : Application() {

    lateinit var mainPresenter: MainPresenter

    override fun onCreate() {
        super.onCreate()

        var network = NetworkModule()
        mainPresenter = MainPresenter(network.buildNetworkApi())
    }


}