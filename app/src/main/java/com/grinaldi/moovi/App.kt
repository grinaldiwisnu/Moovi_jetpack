package com.grinaldi.moovi

import android.app.Application
import com.grinaldi.moovi.di.AppComponent
import com.grinaldi.moovi.di.DaggerAppComponent
import com.grinaldi.moovi.di.StorageModule

open class App : Application() {
    val appComponent: AppComponent = DaggerAppComponent.builder()
        .storageModule(StorageModule(this))
        .build()
}