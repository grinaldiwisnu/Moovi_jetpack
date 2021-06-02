package com.grinaldi.moovi.data.sources.remote

import android.content.Context
import com.grinaldi.moovi.base.BaseModel
import com.grinaldi.moovi.data.models.Movie
import com.grinaldi.moovi.data.sources.DataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(context: Context) : DataSource {

    private val mApiService = RetrofitService.getApiService(context)
    private var compositeDisposable: CompositeDisposable? = null

    override fun getAllData(type: String, filter: String, callback: DataSource.GetAllDataCallback) {
        mApiService.getAllData(type, filter, "MOVIE_DB_API_KEY")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Callback<BaseModel<List<Movie>>>() {
                override fun onSuccess(model: BaseModel<List<Movie>>) {
                    val newData = model.results ?: listOf()
                    callback.onSuccess(newData)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }

                override fun onStartObserver(disposable: Disposable) {
                    addSubscribe(disposable)
                }

            })
    }

    override fun onClearDisposables() {
        compositeDisposable?.clear()
    }

    fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
            compositeDisposable?.add(disposable)
        }
    }
}