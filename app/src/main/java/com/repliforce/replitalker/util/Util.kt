package com.repliforce.replitalker.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.api.Context
import com.repliforce.replitalker.R

fun ImageView.loadUrl(url: String?, errorDrawable: Int = R.drawable.empty) {
    context?.let {
        val options = RequestOptions()
            .placeholder(progressDrawable(context))
            .error(errorDrawable)
        Glide.with(context.applicationContext)
            .load(url)
            .apply(options)
            .into(this)
    }
}

fun progressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
}