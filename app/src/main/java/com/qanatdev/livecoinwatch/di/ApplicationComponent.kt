package com.qanatdev.livecoinwatch.di

import android.app.Application
import com.qanatdev.livecoinwatch.presentation.CryptoDetailFragment
import com.qanatdev.livecoinwatch.presentation.CryptoPriceListActivity
import com.qanatdev.livecoinwatch.presentation.LiveCoinWatch
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(cryptoPriceListActivity: CryptoPriceListActivity)

    fun inject(cryptoDetailFragment: CryptoDetailFragment)
    fun inject(liveCoinWatch: LiveCoinWatch)

    @Component.Factory
    interface ComponentFactory{

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}