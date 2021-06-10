package com.digi.tmdb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.digi.tmdb.R
import com.squareup.picasso.Picasso


@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load("http://image.tmdb.org/t/p/w500$url").fit().centerCrop()
        .placeholder(R.drawable.user_placeholder)
        .error(R.drawable.user_placeholder_error)
        .centerCrop()
        .into(view);
}