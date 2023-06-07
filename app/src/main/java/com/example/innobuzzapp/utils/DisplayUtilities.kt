package com.example.innobuzzapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast


class DisplayUtilities {
    companion object {
        fun getConnectionStatus(context: Context): Boolean {
            val mConnectivityManager: ConnectivityManager
            val mNetworkInfoMobile: NetworkInfo?
            val mNetworkInfoWifi: NetworkInfo?
            mConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            mNetworkInfoMobile =
                mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            mNetworkInfoWifi = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            try {
                if (mNetworkInfoMobile!!.isConnected) {
                    return true
                } else if (mNetworkInfoWifi!!.isConnected) {
                    return true
                }
            } catch (exception: Exception) {
                exception.printStackTrace();
            }
            return false
        }

        //extension function for log
        fun toast(msg: String, context: Context) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }
}