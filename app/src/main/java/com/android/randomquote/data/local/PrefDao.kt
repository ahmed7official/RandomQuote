package com.android.randomquote.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.android.randomquote.data.models.Quote
import com.android.randomquote.utils.get
import com.android.randomquote.utils.set

class PrefDao(private val pref: SharedPreferences, private val context: Context) {


    companion object {


        private const val KEY_QUOTE = "quote"


    }//companion object

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    var quote: Quote?
        get() = pref[KEY_QUOTE]
        set(value) {
            pref[KEY_QUOTE] = value
        }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    fun clear() = pref.edit(commit = true) { clear() }


}//PrefDao