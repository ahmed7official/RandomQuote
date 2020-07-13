package com.android.randomquote.data.remote


import com.android.randomquote.BuildConfig
import com.android.randomquote.data.models.ApiResponse
import com.android.randomquote.data.models.Quote
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


@JvmSuppressWildcards
interface WebService {



    @GET("quotes/random")
    suspend fun getRandomQuote(): Response<Quote>


    companion object {


        operator fun invoke(): WebService {

            val okHttpClint = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()

            return Retrofit.Builder()
                .client(okHttpClint)
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }//invoke()
    }//companion object

}//WebService