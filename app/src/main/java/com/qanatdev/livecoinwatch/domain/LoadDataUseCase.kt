package com.qanatdev.livecoinwatch.domain

import javax.inject.Inject


class LoadDataUseCase @Inject constructor(
    private val repository: CryptoRepository
) {

     operator fun invoke() = repository.loadData()
}