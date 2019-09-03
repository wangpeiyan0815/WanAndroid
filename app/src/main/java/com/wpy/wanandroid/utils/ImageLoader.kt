package com.wpy.wanandroid.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.wpy.wanandroid.R

class ImageLoader {

    companion object {

        fun image(imageView: ImageView, url: String) {
            Glide.with(imageView.context)
                .load(url)
                .apply(
                    RequestOptions()
                        .error(R.drawable.image_holder)
                        .placeholder(R.drawable.image_holder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(imageView)
        }
    }
}