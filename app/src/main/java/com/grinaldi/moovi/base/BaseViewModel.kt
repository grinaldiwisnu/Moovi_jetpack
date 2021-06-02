package com.grinaldi.moovi.base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grinaldi.moovi.data.sources.DataRepository
import com.grinaldi.moovi.di.Injection

abstract class BaseViewModel(
    application: Application,
    val mRepository: DataRepository = Injection.provideRepository(
        application
    )
) :
    ViewModel() {
    var eventGlobalMessage = MutableLiveData<String>()
    var eventShowProgress = MutableLiveData<Boolean>()
}