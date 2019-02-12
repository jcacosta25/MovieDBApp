package io.jcal.movies_provider.domain.interactor.base

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkUtil @Inject constructor(private val context: Context) {
    val isConnected: Boolean
        get() {
            val connectivityManager =
                context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
}