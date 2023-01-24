package com.example.mycoin6.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoinRetrofit {

    // https://api.bithumb.com/public/ticker/ALL_KRW
    private const val BASE_URL = "https://api.bithumb.com/"

    val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun coinRetrofitClient() : Retrofit {
        return client
    }

}