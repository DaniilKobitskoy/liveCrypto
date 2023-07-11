package com.crypto.money.internet

import com.crypto.money.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LiveResponce {
    @GET("category")
    suspend fun getLive(@Query("CMC_PRO_API_KEY") apiKey: String, @Query("id") id: String): ApiResponse
}