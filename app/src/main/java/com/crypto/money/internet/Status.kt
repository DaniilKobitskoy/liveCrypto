package com.crypto.money.internet

import com.google.gson.annotations.SerializedName

data class Status (
    @SerializedName("timestamp"     ) var timestamp    : String? = null,
    @SerializedName("error_code"    ) var errorCode    : String?    = null,
    @SerializedName("error_message" ) var errorMessage : String? = null,
    @SerializedName("elapsed"       ) var elapsed      : Int?    = null,
    @SerializedName("credit_count"  ) var creditCount  : Int?    = null,
    @SerializedName("notice"        ) var notice       : String? = null

)