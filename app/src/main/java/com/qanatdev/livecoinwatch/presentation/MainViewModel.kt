package com.qanatdev.livecoinwatch.presentation

import androidx.lifecycle.ViewModel
import com.qanatdev.livecoinwatch.domain.GetCryptoListUseCase
import com.qanatdev.livecoinwatch.domain.GetCryptoUseCase
import com.qanatdev.livecoinwatch.domain.LoadDataUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val getCryptoUseCase: GetCryptoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {

    val cryptoList = getCryptoListUseCase()

    fun getDetailInfo(fSym: String) = getCryptoUseCase(fSym)

    fun updateDataVM(){
        loadDataUseCase()
    }
    init {
        loadDataUseCase()
    }

}