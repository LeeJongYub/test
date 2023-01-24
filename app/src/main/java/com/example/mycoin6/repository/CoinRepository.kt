package com.example.mycoin6.repository

import com.example.mycoin6.network.CoinApi
import com.example.mycoin6.network.CoinData
import com.example.mycoin6.network.CoinRetrofit
import retrofit2.Callback

class CoinRepository {

    val client = CoinRetrofit.coinRetrofitClient().create(CoinApi::class.java)

    suspend fun data() = client.getDataApi()

    fun data1(callback: Callback<CoinData>) {
        client.getDataApi1().enqueue(callback)
    }

}