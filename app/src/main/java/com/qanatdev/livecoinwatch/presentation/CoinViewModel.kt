package com.qanatdev.livecoinwatch.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.qanatdev.livecoinwatch.data.repository.CryptoRepositoryImpl
import com.qanatdev.livecoinwatch.domain.GetCryptoListUseCase
import com.qanatdev.livecoinwatch.domain.GetCryptoUseCase
import com.qanatdev.livecoinwatch.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CryptoRepositoryImpl(application)

    private val getCryptoListUseCase = GetCryptoListUseCase(repository)
    private val getCryptoUseCase = GetCryptoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCryptoListUseCase()

    fun getDetailInfo(fSym: String) = getCryptoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }







}