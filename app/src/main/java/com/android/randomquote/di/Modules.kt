package com.android.randomquote.di

import android.content.Context
import com.android.randomquote.data.local.PrefDao
import com.android.randomquote.data.remote.WebService
import com.android.randomquote.data.repositories.QuoteRepository
import com.android.randomquote.ui.quote.QuoteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val prefDaoModule = module {
    single {
        PrefDao(
            androidApplication().getSharedPreferences(
                androidApplication().packageName,
                Context.MODE_PRIVATE
            )
            , get()
        )
    }
}



val networkingModules = module {

    single { WebService() }

}


val viewModelModules = module {

    viewModel { QuoteViewModel(get()) }

}

val repositoriesModules = module {

    single { QuoteRepository(get(), get()) }

}