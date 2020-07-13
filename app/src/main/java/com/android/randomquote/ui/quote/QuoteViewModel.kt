package com.android.randomquote.ui.quote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.randomquote.data.models.Quote
import com.android.randomquote.data.repositories.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {


    val quoteLiveData: MutableLiveData<Quote>
        get() = repository.quoteLiveData

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    fun getRandomQuote() {

        viewModelScope.launch { repository.getRandomQuote() }

    }//getRandomQuote()

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -



}//QuoteViewModel