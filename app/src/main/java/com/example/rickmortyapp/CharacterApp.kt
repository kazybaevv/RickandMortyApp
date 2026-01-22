package com.example.rickmortyapp

import android.app.Application
import com.example.rickmortyapp.di.dataModule
import com.example.rickmortyapp.di.domainModule
import com.example.rickmortyapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CharacterApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CharacterApp)
            androidLogger(Level.DEBUG)
            modules(
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}