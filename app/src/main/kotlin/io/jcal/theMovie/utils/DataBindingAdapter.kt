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
            if (!url.isNullOrEmpty()) {
                Picasso.get()
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(imageView)
            } else {
                imageView.setImageResource(R.mipmap.ic_launcher)
            }
        }
    }
}