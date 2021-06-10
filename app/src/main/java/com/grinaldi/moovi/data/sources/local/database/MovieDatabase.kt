package com.grinaldi.moovi.data.sources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.GenreConverter
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity

@Database(entities = [MovieEntity::class, DetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}