package com.grinaldi.moovi.di

import android.content.Context
import androidx.room.Room
import com.grinaldi.moovi.data.sources.local.database.MovieDao
import com.grinaldi.moovi.data.sources.local.database.MovieDatabase
import com.grinaldi.moovi.data.sources.local.entity.GenreConverter
import com.grinaldi.moovi.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideDatabase(): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
            .addTypeConverter(GenreConverter())
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: MovieDatabase): MovieDao = database.movieDao()
}