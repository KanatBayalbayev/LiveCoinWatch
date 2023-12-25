package com.qanatdev.livecoinwatch.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.qanatdev.livecoinwatch.data.CryptoMapper
import com.qanatdev.livecoinwatch.data.api.ApiFactory
import com.qanatdev.livecoinwatch.data.database.AppDatabase
import com.qanatdev.livecoinwatch.data.workmanager.UpdateData
import com.qanatdev.livecoinwatch.domain.CryptoEntity
import com.qanatdev.livecoinwatch.domain.CryptoRepository
import kotlinx.coroutines.delay


class CryptoRepositoryImpl(
    private val application: Application
) : CryptoRepository {

    private val cryptoInfoDAO = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CryptoMapper()


    override  fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            UpdateData.NAME,
            ExistingWorkPolicy.REPLACE,
            UpdateData.makeRequest()
        )
    }

    override fun getCryptoList(): LiveData<List<CryptoEntity>> {
        return cryptoInfoDAO.getPriceList().map {
            it.map {
                mapper.mapDatabaseToEntity(it)
            }
        }
    }

    override fun getCrypto(fromSymbol: String): LiveData<CryptoEntity> {
        return cryptoInfoDAO.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDatabaseToEntity(it)
        }
    }
}