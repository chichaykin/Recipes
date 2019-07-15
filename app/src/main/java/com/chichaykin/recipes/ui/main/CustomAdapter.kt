package com.chichaykin.recipes.ui.main

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.chichaykin.recipes.R
import com.chichaykin.recipes.network.Recipe
import com.chichaykin.recipes.utils.TextUtils

import java.util.ArrayList


class CustomAdapter(context: Context)
    : ArrayAdapter<Recipe>(context, R.layout.list_item, ArrayList()) {

    private val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.noimage_recipeimage)
            .priority(Priority.HIGH)

    private val rightIcon: Drawable = ContextCompat.getDrawable(
            context, R.drawable.ic_keyboard_arrow_right_black_24dp)!!

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        // Get the data item for this position
        val dataModel = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        val viewHolder: ViewHolder // view lookup cache stored in tag
        if (view == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.list_item, parent, false)
            viewHolder.textView = view!!.findViewById(R.id.text)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.textView!!.text = TextUtils.fromHtml(dataModel!!.title)

        val target = object : SimpleTarget<Drawable>(150, 150) {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>) {
                viewHolder.textView!!.setCompoundDrawablesWithIntrinsicBounds(
                        resource, null, rightIcon, null)
            }
        }

        Glide.with(context)
                .load(dataModel.imageUrl)
                .apply(options)
                .into<SimpleTarget<Drawable>>(target)
        return view
    }

    // View lookup cache
    private class ViewHolder {
        internal var textView: TextView? = null
    }

}
