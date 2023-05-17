package com.example.practicaltestproject

import android.app.Application
import android.os.Build
import com.example.prcaticaltest.appModule
import com.example.prcaticaltest.network.NetworkService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PracticalTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext( this@PracticalTestApp )
            modules( appModule )
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            NetworkService.setUpNetworkCallBack(this)
        }
    }
}