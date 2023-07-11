package com.crypto.money.internet

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptocurrencyService {
    @GET("categories")
    suspend fun getCryptocurrencies(@Query("CMC_PRO_API_KEY") apiKey: String): Cryptocurrency
}


