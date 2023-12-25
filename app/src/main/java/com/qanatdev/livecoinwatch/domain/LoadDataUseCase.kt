package com.qanatdev.livecoinwatch.domain


class LoadDataUseCase(
    private val repository: CryptoRepository
) {

     operator fun invoke() = repository.loadData()
}