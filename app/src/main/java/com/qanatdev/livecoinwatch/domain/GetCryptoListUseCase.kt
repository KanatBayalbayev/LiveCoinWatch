package com.qanatdev.livecoinwatch.domain

import javax.inject.Inject


class GetCryptoListUseCase @Inject constructor(
    private val repository: CryptoRepository
) {

    operator fun invoke() = repository.getCryptoList()
}