package com.crypto.money.internet
import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("num_tokens") var numTokens: Int? = null,
    @SerializedName("avg_price_change") var avgPriceChange: Double? = null,
    @SerializedName("market_cap") var marketCap: Double? = null,
    @SerializedName("market_cap_change") var marketCapChange: Double? = null,
    @SerializedName("volume") var volume: Double? = null,
    @SerializedName("volume_change") var volumeChange: Double? = null,
    @SerializedName("last_updated") var lastUpdated: String? = null
)
