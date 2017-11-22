package com.chichaykin.recipes.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.chichaykin.recipes.App
import com.chichaykin.recipes.R
import com.chichaykin.recipes.ui.MvpActivity
import com.chichaykin.recipes.ui.model.RecipeModel
import com.chichaykin.recipes.utils.TextUtils
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : MvpActivity<DetailView, DetailPresenter>(), DetailView {

    override val presenter
        get() = (applicationContext as App).detailPresenter

    private lateinit var model: RecipeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (intent.extras != null) {
            model = intent.extras.getParcelable(RECIPE)
        } else if (savedInstanceState != null) {
            model = savedInstanceState.getParcelable(RECIPE)
        }

        title = TextUtils.fromHtml(model.title)

        setupImage(model.imageUrl)
        setupIngredients(model.id)

        viewInstruction.setOnClickListener { navigate(model.recipeUrl) }
        viewOriginal.setOnClickListener { navigate(model.sourceUrl) }

        publisherName.text = model.publisher
        rankText.text = getString(R.string.social_rank, model.socialRank)
    }

    public override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putParcelable(RECIPE, model)

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
    }

    private fun navigate(recipeUrl: String) {
        WebActivity.runIntent(this, recipeUrl, title)
    }

    private fun setupIngredients(id: String) {
        presenter.subscribeToIngredients(id)
    }

    private fun setupImage(imageUrl: String) {
        val options = RequestOptions()
                .placeholder(R.drawable.noimage_recipeimage)
                .priority(Priority.HIGH)

        Glide.with(this)
                .load(imageUrl)
                .apply(options)
                .into(imageView)
    }

    override fun show(ingredients: List<String>) {
        listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredients)
    }

    override fun showError(ingredients: String) {
        Toast.makeText(this, ingredients, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val RECIPE = "recipe"

        fun runIntent(context: Context, recipe: RecipeModel) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(RECIPE, recipe)
            context.startActivity(intent)
        }
    }


}
