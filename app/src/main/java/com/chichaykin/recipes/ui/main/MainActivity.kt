package com.chichaykin.recipes.ui.main

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.chichaykin.recipes.App
import com.chichaykin.recipes.R
import com.chichaykin.recipes.network.Recipe
import com.chichaykin.recipes.ui.MvpActivity


class MainActivity : MvpActivity<MainView, MainPresenter>(), MainView {
    // As it was restriction don't use dagger, so just return application's instance of presenter
    override val presenter: MainPresenter
        get() = (applicationContext as App).mainPresenter

    private lateinit var listView: ListView

    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.list_view)
        adapter = CustomAdapter(this)
        listView.adapter = adapter
    }

    override fun show(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_LONG)
    }

    override fun show(list: List<Recipe>?) {
        adapter.addAll(list)
    }

}
