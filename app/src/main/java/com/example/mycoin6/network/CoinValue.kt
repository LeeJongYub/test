package com.example.mycoin6.network

import com.google.gson.annotations.SerializedName

data class CoinValue(
    @SerializedName("acc_trade_value")
    val accTradeValue: String,
    val acc_trade_value_24H: String,
    val closing_price: String,
    val fluctate_24H: String,
    val fluctate_rate_24H: String,
    val max_price: String,
    val min_price: String,
    val opening_price: String,
    val prev_closing_price: String,
    val units_traded: String,
    val units_traded_24H: String
)