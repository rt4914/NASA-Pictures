package com.obvious.nasapictures.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.obvious.nasapictures.R

/** Holds all custom binding adapters that bind to [ImageView]. */
class ImageViewBindingAdapters {
  /**
   * Allows binding drawables to an [ImageView] via "android:src".
   * Reference: https://stackoverflow.com/a/35809319/3689782.
   */
  companion object {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageDrawable(imageView: ImageView, imageUrl: String?) {
      val requestOptions: RequestOptions =
        RequestOptions().placeholder(R.drawable.nasa_logo).centerCrop()
      Glide.with(imageView.context)
        .load(imageUrl)
        .apply(requestOptions)
        .into(imageView)
    }
  }
}
