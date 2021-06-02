package com.grinaldi.moovi.base

interface BaseDataSource {
    interface ResponseCallback<T> {
        fun onSuccess(data: T)
        fun onFinish()
        fun onFailed(statusCode: Int, errorMessage: String? = "")
    }

    fun onClearDisposables()
}