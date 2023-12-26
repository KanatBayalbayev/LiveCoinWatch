package com.qanatdev.livecoinwatch.di

import androidx.lifecycle.ViewModel
import com.qanatdev.livecoinwatch.presentation.CoinViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindMainViewModel(coinViewModel: CoinViewModel): ViewModel
}