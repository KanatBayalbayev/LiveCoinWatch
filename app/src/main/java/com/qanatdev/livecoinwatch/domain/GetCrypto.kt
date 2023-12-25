package com.qanatdev.livecoinwatch.domain


class GetCrypto(
    private val repository: CryptoRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCrypto(fromSymbol)
}