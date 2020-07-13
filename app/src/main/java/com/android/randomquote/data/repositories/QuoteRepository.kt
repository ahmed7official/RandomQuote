package com.android.randomquote.data.repositories

import androidx.lifecycle.MutableLiveData
import com.android.randomquote.data.local.PrefDao
import com.android.randomquote.data.models.ApiResponse
import com.android.randomquote.data.models.Quote
import com.android.randomquote.data.remote.SafeApiRequest
import com.android.randomquote.data.remote.WebService


class QuoteRepository(private val webService: WebService, private val prefDao: PrefDao) :
    SafeApiRequest() {


    /*
    * since quote contains only text & author and we will save one quote, it's not necessary to create database and we can use
    * shared preferences instead
    * */


    val quoteLiveData = MutableLiveData<Quote>()

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    suspend fun getRandomQuote() {

        val response: ApiResponse<Quote> = apiRequest { webService.getRandomQuote() }


        response.onSuccess { quote ->

            prefDao.quote = quote

            quoteLiveData.value = quote

        }//onSuccess()


        response.onFailure { message, code ->
            quoteLiveData.value = prefDao.quote
        }//onFailure()

    }//getRandomQuote()

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


}//QuoteRepository