package com.qanatdev.livecoinwatch.domain


class GetCryptoUseCase(
    private val repository: CryptoRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCrypto(fromSymbol)
}