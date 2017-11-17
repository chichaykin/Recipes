package com.chichaykin.recipes.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chichaykin.recipes.R;
import com.chichaykin.recipes.network.Recipe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<Recipe> {

    private static final String TAG = "CustomAdapter";
    private int lastPosition = -1;

    public CustomAdapter(Context context) {
        super(context, R.layout.list_item, new ArrayList<Recipe>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Recipe dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.textView = convertView.findViewById(R.id.text);
            viewHolder.imageView = convertView.findViewById(R.id.image);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.textView.setText(dataModel.getTitle());

//        SimpleTarget<Drawable> target = new SimpleTarget<Drawable>(100, 100) {
//            @Override
//            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                holder.textView.setCompoundDrawablesWithIntrinsicBounds(
//                        null, null, resource, null);
//            }
//        };

        Log.d(TAG,"Try loading picture from: " + dataModel.getImageUrl());

        Glide.with(getContext())
                .load(dataModel.getImageUrl())
                .into(viewHolder.imageView);
                //.into(target);
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
