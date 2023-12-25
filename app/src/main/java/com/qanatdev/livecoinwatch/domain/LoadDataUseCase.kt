package com.qanatdev.livecoinwatch.domain


class LoadDataUseCase(
    private val repository: CryptoRepository
) {

    suspend operator fun invoke() = repository.loadData()
}