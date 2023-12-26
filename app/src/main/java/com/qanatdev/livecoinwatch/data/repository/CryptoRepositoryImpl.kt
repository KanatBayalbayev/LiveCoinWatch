package com.qanatdev.livecoinwatch.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.qanatdev.livecoinwatch.data.CryptoMapper
import com.qanatdev.livecoinwatch.data.api.ApiFactory
import com.qanatdev.livecoinwatch.data.database.AppDatabase
import com.qanatdev.livecoinwatch.data.database.CryptoInfoDao
import com.qanatdev.livecoinwatch.data.workmanager.UpdateData
import com.qanatdev.livecoinwatch.domain.CryptoEntity
import com.qanatdev.livecoinwatch.domain.CryptoRepository
import kotlinx.coroutines.delay
import javax.inject.Inject


class CryptoRepositoryImpl @Inject constructor(
    private val mapper: CryptoMapper,
    private val cryptoInfoDAO: CryptoInfoDao,
    private val application: Application
) : CryptoRepository {



    override fun loadData() {
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