package com.qanatdev.livecoinwatch.presentation

import android.app.Application
import androidx.work.Configuration
import com.qanatdev.livecoinwatch.data.CryptoMapper
import com.qanatdev.livecoinwatch.data.api.ApiFactory
import com.qanatdev.livecoinwatch.data.database.AppDatabase
import com.qanatdev.livecoinwatch.data.workmanager.UpdateDataFactory
import com.qanatdev.livecoinwatch.di.DaggerApplicationComponent
import javax.inject.Inject

class CryptoApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: UpdateDataFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()


}