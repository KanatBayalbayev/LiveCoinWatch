package com.qanatdev.livecoinwatch.di

import android.app.Application
import com.qanatdev.livecoinwatch.presentation.CoinDetailFragment
import com.qanatdev.livecoinwatch.presentation.CoinPriceListActivity
import com.qanatdev.livecoinwatch.presentation.CryptoApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(coinPriceListActivity: CoinPriceListActivity)

    fun inject(coinDetailFragment: CoinDetailFragment)
    fun inject(cryptoApp: CryptoApp)

    @Component.Factory
    interface ComponentFactory{

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}