package com.qanatdev.livecoinwatch.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.qanatdev.livecoinwatch.data.CryptoMapper
import com.qanatdev.livecoinwatch.data.api.ApiFactory
import com.qanatdev.livecoinwatch.data.database.AppDatabase
import com.qanatdev.livecoinwatch.domain.CryptoEntity
import com.qanatdev.livecoinwatch.domain.CryptoRepository
import kotlinx.coroutines.delay


class CryptoRepositoryImpl(
    private val application: Application
) : CryptoRepository {

    private val cryptoInfoDAO = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CryptoMapper()


    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCryptoInfoDTO(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDTOtoDatabase(it) }
                cryptoInfoDAO.insertPriceList(dbModelList)
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            delay(10000)
        }
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