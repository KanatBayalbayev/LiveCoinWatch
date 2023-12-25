package com.qanatdev.livecoinwatch.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.qanatdev.livecoinwatch.pojo.CoinInfo

data class Datum (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null
)
