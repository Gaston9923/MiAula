package com.example.miaula

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.bu_android.di.appModule
import com.example.miaula.di.miAulaApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MiAulaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MiAulaApp)
            modules(appModule, miAulaApiModule)
        }
    }
}