package com.android.randomquote.data.models

import com.google.gson.annotations.SerializedName

data class Quote(

    @SerializedName("en")
    val quoteEn: String? = null,

    @SerializedName("author")
    val author: String? = null


)