package com.qanatdev.livecoinwatch.data

import android.media.MediaCodec.CryptoInfo
import com.google.gson.Gson
import com.qanatdev.livecoinwatch.data.api.models.CryptoInfoDTO
import com.qanatdev.livecoinwatch.data.api.models.CryptoJsonObjectDTO
import com.qanatdev.livecoinwatch.data.api.models.CryptoNamesListDTO
import com.qanatdev.livecoinwatch.data.database.CryptoInfoDatabase
import com.qanatdev.livecoinwatch.domain.CryptoEntity

class CryptoMapper {


    fun mapDTOtoDatabase(
        cryptoInfoDTO: CryptoInfoDTO
    ): CryptoInfoDatabase {
        return CryptoInfoDatabase(
            type = cryptoInfoDTO.type,
            market = cryptoInfoDTO.market,
            fromSymbol = cryptoInfoDTO.fromSymbol,
            toSymbol = cryptoInfoDTO.toSymbol,
            flags = cryptoInfoDTO.flags,
            price = cryptoInfoDTO.price,
            lastUpdate = cryptoInfoDTO.lastUpdate,
            lastVolume = cryptoInfoDTO.lastVolume,
            lastVolumeTo = cryptoInfoDTO.lastVolumeTo,
            lastTradeId = cryptoInfoDTO.lastTradeId,
            volumeDay = cryptoInfoDTO.volumeDay,
            volumeDayTo = cryptoInfoDTO.volumeDayTo,
            volume24Hour = cryptoInfoDTO.volume24Hour,
            volume24HourTo = cryptoInfoDTO.volume24HourTo,
            openDay = cryptoInfoDTO.openDay,
            highDay = cryptoInfoDTO.highDay,
            open24Hour = cryptoInfoDTO.open24Hour,
            high24Hour = cryptoInfoDTO.high24Hour,
            low24Hour = cryptoInfoDTO.low24Hour,
            lastMarket = cryptoInfoDTO.lastMarket,
            volumeHour = cryptoInfoDTO.volumeHour,
            volumeHourTo = cryptoInfoDTO.volumeHourTo,
            openHour = cryptoInfoDTO.openHour,
            highHour = cryptoInfoDTO.highHour,
            lowHour = cryptoInfoDTO.lowHour,
            topTierVolume24Hour = cryptoInfoDTO.topTierVolume24Hour,
            topTierVolume24HourTo = cryptoInfoDTO.topTierVolume24HourTo,
            change24Hour = cryptoInfoDTO.change24Hour,
            changePCT24Hour = cryptoInfoDTO.changePCT24Hour,
            changeDay = cryptoInfoDTO.changeDay,
            changePCTDay = cryptoInfoDTO.changePCTDay,
            supply = cryptoInfoDTO.supply,
            mktCap = cryptoInfoDTO.mktCap,
            totalVolume24Hour = cryptoInfoDTO.totalVolume24Hour,
            totalVolume24HourTo = cryptoInfoDTO.totalVolume24HourTo,
            totalTopTierVolume24Hour = cryptoInfoDTO.totalTopTierVolume24Hour,
            totalTopTierVolume24HourTo = cryptoInfoDTO.totalTopTierVolume24HourTo,
            imageUrl = cryptoInfoDTO.imageUrl,
            lowDay = cryptoInfoDTO.lowDay
        )

    }

    fun mapJsonContainerToListCryptoInfoDTO(jsonContainer: CryptoJsonObjectDTO): List<CryptoInfoDTO> {
        val result = mutableListOf<CryptoInfoDTO>()
        val jsonObject = jsonContainer.cryptoJsonObject ?: return result
        val cryptoKeysSet = jsonObject.keySet()
        for (coinKey in cryptoKeysSet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CryptoInfoDTO::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CryptoNamesListDTO): String {
        return namesListDto.cryptoNames?.map {
            it.cryptoName?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDatabaseToEntity(cryptoInfoDatabase: CryptoInfoDatabase) = CryptoEntity(
        type = cryptoInfoDatabase.type,
        market = cryptoInfoDatabase.market,
        fromSymbol = cryptoInfoDatabase.fromSymbol,
        toSymbol = cryptoInfoDatabase.toSymbol,
        flags = cryptoInfoDatabase.flags,
        price = cryptoInfoDatabase.price,
        lastUpdate = cryptoInfoDatabase.lastUpdate,
        lastVolume = cryptoInfoDatabase.lastVolume,
        lastVolumeTo = cryptoInfoDatabase.lastVolumeTo,
        lastTradeId = cryptoInfoDatabase.lastTradeId,
        volumeDay = cryptoInfoDatabase.volumeDay,
        volumeDayTo = cryptoInfoDatabase.volumeDayTo,
        volume24Hour = cryptoInfoDatabase.volume24Hour,
        volume24HourTo = cryptoInfoDatabase.volume24HourTo,
        openDay = cryptoInfoDatabase.openDay,
        highDay = cryptoInfoDatabase.highDay,
        open24Hour = cryptoInfoDatabase.open24Hour,
        high24Hour = cryptoInfoDatabase.high24Hour,
        low24Hour = cryptoInfoDatabase.low24Hour,
        lastMarket = cryptoInfoDatabase.lastMarket,
        volumeHour = cryptoInfoDatabase.volumeHour,
        volumeHourTo = cryptoInfoDatabase.volumeHourTo,
        openHour = cryptoInfoDatabase.openHour,
        highHour = cryptoInfoDatabase.highHour,
        lowHour = cryptoInfoDatabase.lowHour,
        topTierVolume24Hour = cryptoInfoDatabase.topTierVolume24Hour,
        topTierVolume24HourTo = cryptoInfoDatabase.topTierVolume24HourTo,
        change24Hour = cryptoInfoDatabase.change24Hour,
        changePCT24Hour = cryptoInfoDatabase.changePCT24Hour,
        changeDay = cryptoInfoDatabase.changeDay,
        changePCTDay = cryptoInfoDatabase.changePCTDay,
        supply = cryptoInfoDatabase.supply,
        mktCap = cryptoInfoDatabase.mktCap,
        totalVolume24Hour = cryptoInfoDatabase.totalVolume24Hour,
        totalVolume24HourTo = cryptoInfoDatabase.totalVolume24HourTo,
        totalTopTierVolume24Hour = cryptoInfoDatabase.totalTopTierVolume24Hour,
        totalTopTierVolume24HourTo = cryptoInfoDatabase.totalTopTierVolume24HourTo,
        imageUrl = cryptoInfoDatabase.imageUrl,
        lowDay = cryptoInfoDatabase.lowDay
    )

}