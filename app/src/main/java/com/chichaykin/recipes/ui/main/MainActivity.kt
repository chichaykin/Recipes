package com.chichaykin.recipes.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.chichaykin.recipes.App
import com.chichaykin.recipes.R
import com.chichaykin.recipes.network.Recipe
import com.chichaykin.recipes.ui.MvpActivity
import com.chichaykin.recipes.ui.detail.DetailActivity
import com.chichaykin.recipes.ui.model.RecipeModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpActivity<MainView, MainPresenter>(), MainView {

    // As it was restriction don't use dagger, so just return application's instance of presenter
    override val presenter: MainPresenter
        get() = (applicationContext as App).mainPresenter

    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CustomAdapter(this)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            presenter.onClick(adapter.getItem(position))
        }

        searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val text = s.toString()
                presenter.search(text)
            }
        })
    }

    override fun showErrorMessage(error: String) {
        emptyItems()
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun show(list: List<Recipe>) {
        adapter.clear()
        if (list.isEmpty()) {
            noDataText.visibility = VISIBLE
            listView.visibility = GONE
        } else {
            adapter.addAll(list)
            listView.visibility = VISIBLE
            noDataText.visibility = GONE
        }
    }

    override fun emptyItems() {
        adapter.clear()
        noDataText.visibility = VISIBLE
        listView.visibility = GONE
        noDataText.text = getText(R.string.no_list_data)
    }

    override fun showProgress() {
        adapter.clear()
        noDataText.text = getText(R.string.loading)
        noDataText.visibility = VISIBLE
        listView.visibility = GONE
    }

    override fun showDetail(model: RecipeModel) {
        DetailActivity.runIntent(this, model)
    }
}
