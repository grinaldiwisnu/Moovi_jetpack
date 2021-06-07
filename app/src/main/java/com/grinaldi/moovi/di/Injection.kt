package com.grinaldi.moovi.di

import com.grinaldi.moovi.data.Repository
import com.grinaldi.moovi.data.sources.remote.RemoteDataSource

object Injection {
    fun provideRepository(): Repository {
        val remoteRepository = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteRepository)
    }
}