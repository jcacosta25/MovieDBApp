package io.jcal.provider.domain.interactor.base

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

@Suppress("DEPRECATION")
class NetworkUtil @Inject constructor(private val context: Context) {
	val isConnected: Boolean
		get() {
			val manager =
				context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
			return manager.activeNetwork?.let { network ->
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
