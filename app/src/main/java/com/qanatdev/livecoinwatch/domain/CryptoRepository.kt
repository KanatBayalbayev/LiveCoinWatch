package com.qanatdev.livecoinwatch.domain

import androidx.lifecycle.LiveData

interface CryptoRepository {

    fun getCryptoList(): LiveData<List<CryptoEntity>>

    fun getCrypto(fromSymbol: String): LiveData<CryptoEntity>


    fun loadData()

}