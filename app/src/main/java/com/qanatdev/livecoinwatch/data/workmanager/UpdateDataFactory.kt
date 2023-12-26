package com.qanatdev.livecoinwatch.data.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.qanatdev.livecoinwatch.data.CryptoMapper
import com.qanatdev.livecoinwatch.data.api.ApiService
import com.qanatdev.livecoinwatch.data.database.CryptoInfoDao
import javax.inject.Inject

class UpdateDataFactory @Inject constructor(
    private val mapper: CryptoMapper,
    private val cryptoInfoDAO: CryptoInfoDao,
    private val apiService: ApiService,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return UpdateData(
            appContext,
            workerParameters,
            mapper,
            cryptoInfoDAO,
            apiService
        )
    }
}