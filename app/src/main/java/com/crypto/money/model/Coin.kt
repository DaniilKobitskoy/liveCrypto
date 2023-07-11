package com.crypto.money.model

import com.google.gson.annotations.SerializedName

data class Coin(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val num_market_pairs: Int,
    val date_added: String,
    val tags: List<String>,
    val max_supply: Double?,
    val circulating_supply: Double,
    val total_supply: Double,
    val is_active: Int,
    val infinite_supply: Boolean,
    val platform: Any?,
    val cmc_rank: Int,
    val is_fiat: Int,
    val self_reported_circulating_supply: Any?,
    val self_reported_market_cap: Any?,
    val tvl_ratio: Any?,
    val last_updated: String,
    val quote: Quote
)