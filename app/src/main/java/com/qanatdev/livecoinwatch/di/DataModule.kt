package com.qanatdev.livecoinwatch.di

import android.app.Application
import com.qanatdev.livecoinwatch.data.database.AppDatabase
import com.qanatdev.livecoinwatch.data.database.CryptoInfoDao
import com.qanatdev.livecoinwatch.data.repository.CryptoRepositoryImpl
import com.qanatdev.livecoinwatch.domain.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCryptoRepository(cryptoRepositoryImpl: CryptoRepositoryImpl): CryptoRepository

    companion object{

        @Provides
        fun provideCryptoInfoDAO(application: Application):CryptoInfoDao{
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}