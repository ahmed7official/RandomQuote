package com.android.randomquote.ui.quote

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.randomquote.R
import com.android.randomquote.data.models.Quote
import com.android.randomquote.databinding.ActivityQuoteBinding
import com.google.android.material.button.MaterialButton
import org.koin.android.ext.android.inject

class QuoteActivity : AppCompatActivity() {

    private val viewModel by inject<QuoteViewModel>()

    private var binding: ActivityQuoteBinding? = null

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quote)

        window?.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        initObservers()
        initListeners()


    }//onCreate()

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private fun initListeners() {

        binding?.btnGetQuote?.setOnClickListener(btnGetQuoteClickListener)

    }//initListeners()

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private fun initObservers() {

        viewModel.quoteLiveData.observe(this, quoteObserver)

    }//initObservers()

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private val btnGetQuoteClickListener = View.OnClickListener {

        (it as MaterialButton).apply {
            text = getString(R.string.please_wait)
            isEnabled = false
        }//apply

        viewModel.getRandomQuote()

    }//btnGetQuoteClickListener

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private val quoteObserver = Observer<Quote> { quote ->

        if (quote != null) {

            binding?.tvQuote?.text = getString(R.string.quote_formatted, quote.quoteEn)
            binding?.tvAuthor?.text = quote.author.toString()

        }else {
            Toast.makeText(this, R.string.try_again, Toast.LENGTH_SHORT).show()
        }

        binding?.btnGetQuote?.apply {
            text = getString(R.string.get_quote)
            isEnabled = true
        }//apply

    }//quoteObserver

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


}//QuoteActivity