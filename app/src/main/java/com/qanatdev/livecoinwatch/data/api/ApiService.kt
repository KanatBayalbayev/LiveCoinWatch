package com.qanatdev.livecoinwatch.data.api

import com.qanatdev.livecoinwatch.data.api.models.CryptoJsonObjectDTO
import com.qanatdev.livecoinwatch.data.api.models.CryptoNamesListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CryptoNamesListDTO

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): CryptoJsonObjectDTO

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}