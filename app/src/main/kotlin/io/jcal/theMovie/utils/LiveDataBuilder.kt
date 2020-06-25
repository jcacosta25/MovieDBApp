package io.jcal.theMovie.utils

import androidx.lifecycle.LiveData

class LiveDataBuilder<T> : LiveData<T>() {

    companion object {
        fun <T> create(data: T): LiveData<T> {
            val absentLiveData = LiveDataBuilder<T>()
            absentLiveData.postValue(data)
            return absentLiveData
        }
    }
}
