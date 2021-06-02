package com.grinaldi.moovi.views

import android.app.Application
import android.content.Context

class App : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun appContext(): Context? {
            return instance?.applicationContext
        }
    }
}