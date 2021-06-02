package com.grinaldi.moovi.di

import android.content.Context
import com.grinaldi.moovi.data.sources.DataRepository
import com.grinaldi.moovi.data.sources.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): DataRepository {
        return DataRepository.getInstance(RemoteDataSource(context))
    }
}