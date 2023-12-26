package com.qanatdev.livecoinwatch.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.qanatdev.livecoinwatch.data.CryptoMapper
import com.qanatdev.livecoinwatch.data.database.CryptoDao
import com.qanatdev.livecoinwatch.data.workmanager.UpdateData
import com.qanatdev.livecoinwatch.domain.CryptoEntity
import com.qanatdev.livecoinwatch.domain.CryptoRepository
import javax.inject.Inject


class CryptoRepositoryImpl @Inject constructor(
    private val mapper: CryptoMapper,
    private val cryptoDAO: CryptoDao,
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
        return cryptoDAO.getPriceList().map {
            it.map {
                mapper.mapDatabaseToEntity(it)
            }
        }
    }

    override fun getCrypto(fromSymbol: String): LiveData<CryptoEntity> {
        return cryptoDAO.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDatabaseToEntity(it)
        }
    }
}