package com.example.mycoin6.network

import retrofit2.Call
import retrofit2.http.GET

interface CoinApi {

    @GET("public/ticker/ALL_KRW")
    suspend fun getDataApi() : CoinData

    @GET("public/ticker/ALL_KRW")
    fun getDataApi1(): Call<CoinData>
}