package com.grinaldi.moovi.data.sources

import com.grinaldi.moovi.base.BaseDataSource
import com.grinaldi.moovi.data.models.Movie

interface DataSource : BaseDataSource {

    // remote source
    fun getAllData(type: String, filter: String, callback: GetAllDataCallback)

    // callback
    interface GetAllDataCallback : BaseDataSource.ResponseCallback<List<Movie>>

    interface GetDataByIdCallback : BaseDataSource.ResponseCallback<Movie>
}