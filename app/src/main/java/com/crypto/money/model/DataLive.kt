package com.crypto.money.model

import com.google.gson.annotations.SerializedName

data class DataLive(
    val id: String,
    val name: String,
    val title: String,
    val description: String,
    val num_tokens: Int,
    val last_updated: String,
    val avg_price_change: Double,
    val market_cap: Double,
    val market_cap_change: Double,
    val volume: Double,
    val volume_change: Double,
    val coins: List<Coin>
)