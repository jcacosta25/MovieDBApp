package io.jcal.theMovie.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import io.jcal.theMovie.R

class DataBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("android:image_url")
        fun loadImage(imageView: AppCompatImageView, url: String?) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.background_place_holder)
                .error(R.drawable.background_place_holder)
                .fit()
                .centerCrop()
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("android:poster_url")
        fun loadPoster(imageView: AppCompatImageView, url: String?) {
            imageView.apply {
                if (url.isNullOrEmpty()) {
                    setImageResource(R.drawable.background_place_holder)
                } else {
                    transitionName = url
                    Picasso.get()
                        .load(url)
                        .placeholder(R.drawable.background_place_holder)
                        .error(R.drawable.background_place_holder)
                        .fit()
                        .centerCrop()
                        .into(this)
                }
            }
        }
    }
}
