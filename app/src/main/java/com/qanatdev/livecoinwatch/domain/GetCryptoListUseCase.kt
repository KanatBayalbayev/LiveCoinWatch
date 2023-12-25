package com.qanatdev.livecoinwatch.domain


class GetCryptoListUseCase(
    private val repository: CryptoRepository
) {

    operator fun invoke() = repository.getCryptoList()
}