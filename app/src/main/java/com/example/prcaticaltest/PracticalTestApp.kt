package com.example.practicaltestproject

import android.app.Application
import com.example.prcaticaltest.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PracticalTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext( this@PracticalTestApp )
            modules( appModule )
        }
    }
}