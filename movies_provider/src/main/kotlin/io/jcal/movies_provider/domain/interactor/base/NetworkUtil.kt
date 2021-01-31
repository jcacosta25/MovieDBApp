package io.jcal.movies_provider.domain.interactor.base

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject

@Suppress("DEPRECATION")
class NetworkUtil @Inject constructor(private val context: Context) {
    val isConnected: Boolean
        get() {
            val manager =
                context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT < 23) {
                val networkInfo = manager.activeNetworkInfo
                networkInfo?.let {
                    networkInfo.isConnected && (networkInfo.type == ConnectivityManager.TYPE_WIFI || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
                } ?: false
            } else {
                manager.activeNetwork?.let { network ->
                    manager.getNetworkCapabilities(network)?.let {
                        return (
                            it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || it.hasTransport(
                                NetworkCapabilities.TRANSPORT_WIFI
                            )
                            )
                    }
                }.let {
                    false
                }
            }
        }
}
