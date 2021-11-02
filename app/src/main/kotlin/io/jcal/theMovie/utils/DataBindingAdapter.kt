package io.jcal.theMovie.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import io.jcal.theMovie.R

class DataBindingAdapter {
	companion object {
		@JvmStatic
		@BindingAdapter("android:image_url")
		fun loadImage(imageView: AppCompatImageView? = null, url: String? = null) {
			imageView?.transitionName = url
			url.takeIf { it.isNullOrBlank().not() }?.let {
				Picasso.get()
					.load(url)
					.placeholder(R.drawable.background_place_holder)
					.error(R.drawable.background_place_holder)
					.fit()
					.centerCrop()
					.into(imageView)
			} ?: run {
				imageView?.setImageResource(R.drawable.background_place_holder)
			}
		}
	}
}
