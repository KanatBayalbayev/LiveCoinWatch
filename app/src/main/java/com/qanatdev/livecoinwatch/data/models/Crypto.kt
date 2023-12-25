package com.qanatdev.livecoinwatch.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Crypto (
    @SerializedName("Name")
    @Expose
    val name: String? = null
)
