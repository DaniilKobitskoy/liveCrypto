package com.crypto.money.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(

    val status: Status,
    val data: DataLive
)