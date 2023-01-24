package com.example.mycoin6.network

import com.example.mycoin6.datamanufacture.CoinDataUnChanged

data class CoinData (
    val status : String,
    val data : Map<String, CoinDataUnChanged>
)