package com.qanatdev.livecoinwatch.di

import android.app.Application
import com.qanatdev.livecoinwatch.presentation.CoinDetailFragment
import com.qanatdev.livecoinwatch.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(coinPriceListActivity: CoinPriceListActivity)

    fun inject(coinDetailFragment: CoinDetailFragment)

    @Component.Factory
    interface ComponentFactory{

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}