package com.crypto.money.model

import com.google.gson.annotations.SerializedName

data class Status(
    val timestamp: String,
    val error_code: Int,
    val error_message: String?,
    val elapsed: Int,
    val credit_count: Int,
    val notice: String?
)