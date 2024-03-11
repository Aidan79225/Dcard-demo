package com.aidan.dcard

import android.app.Application
import com.aidan.dcard.di.appModule
import com.aidan.dcard.di.dbModule
import com.aidan.dcard.di.domainModule
import com.aidan.dcard.di.networkModule
import com.aidan.dcard.di.utilModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule, utilModule, networkModule, dbModule, domainModule)
        }
    }
}