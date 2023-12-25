package com.qanatdev.livecoinwatch.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: Crypto? = null
)
