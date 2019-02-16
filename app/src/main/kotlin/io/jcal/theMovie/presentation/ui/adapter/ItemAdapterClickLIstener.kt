package io.jcal.theMovie.presentation.ui.adapter

import android.view.View

interface ItemAdapterClickLIstener<T> {
    fun onItemClick(view: View, position: Int, item: T)
}