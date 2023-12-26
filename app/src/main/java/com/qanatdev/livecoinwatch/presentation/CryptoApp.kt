package com.qanatdev.livecoinwatch.presentation

import android.app.Application
import com.qanatdev.livecoinwatch.di.DaggerApplicationComponent

class CryptoApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}