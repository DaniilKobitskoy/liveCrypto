package com.crypto.money.internet

import com.google.gson.annotations.SerializedName

data class Cryptocurrency(
    @SerializedName("status") var status: Status? = null,
    @SerializedName("data") var data: MutableList<Data>? = null
)

