package com.example.prcaticaltest.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService


object NetworkService {

    private var connectivityManager: ConnectivityManager? = null
    private var networkCallback: ConnectivityManager.NetworkCallback? = null
    private var isAvailable : Boolean = false

    fun checkForInternet(context: Context) : Boolean {
        if (connectivityManager == null)
            connectivityManager = getSystemService(context, ConnectivityManager::class.java)

        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val activeNetwork: NetworkInfo? = connectivityManager?.activeNetworkInfo
            activeNetwork?.isConnected ?: false
        } else {
            isAvailable
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun setUpNetworkCallBack(context: Context) {
        if (connectivityManager == null)
            connectivityManager = getSystemService(context, ConnectivityManager::class.java)

        if (networkCallback == null) {
            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    isAvailable = true
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    isAvailable = false
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    isAvailable = false
                }
            }
            connectivityManager?.registerDefaultNetworkCallback(networkCallback as ConnectivityManager.NetworkCallback)
        }
    }
}