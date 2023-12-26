package com.qanatdev.livecoinwatch.di

import android.app.Application
import com.qanatdev.livecoinwatch.data.api.ApiFactory
import com.qanatdev.livecoinwatch.data.api.ApiService
import com.qanatdev.livecoinwatch.data.database.AppDatabase
import com.qanatdev.livecoinwatch.data.database.CryptoDao
import com.qanatdev.livecoinwatch.data.repository.CryptoRepositoryImpl
import com.qanatdev.livecoinwatch.domain.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCryptoRepository(cryptoRepositoryImpl: CryptoRepositoryImpl): CryptoRepository

    companion object{

        @Provides
        @ApplicationScope
        fun provideCryptoInfoDAO(application: Application):CryptoDao{
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}