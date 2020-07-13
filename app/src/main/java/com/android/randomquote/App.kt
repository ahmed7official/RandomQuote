package com.android.randomquote

import android.app.Application
import com.android.randomquote.di.networkingModules
import com.android.randomquote.di.prefDaoModule
import com.android.randomquote.di.repositoriesModules
import com.android.randomquote.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModules, prefDaoModule, repositoriesModules, networkingModules))
        }//startKoin


    }//onCreate()
}