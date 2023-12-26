package com.qanatdev.livecoinwatch.domain

import javax.inject.Inject


class GetCryptoUseCase @Inject constructor(
    private val repository: CryptoRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCrypto(fromSymbol)
}