package com.example.geekbrains

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geekbrains.lesson2.AppState
import com.example.geekbrains.lesson2.repositoty.Repo
import com.example.geekbrains.lesson2.repositoty.RepoImpl
import java.lang.Thread.sleep

class Lesson2ViewModel : ViewModel() {
    private val liveData = MutableLiveData<AppState>()
    private val repo: Repo = RepoImpl()

    private fun getDataFromSource() {
//        liveData.value = (AppState.Loading)
//        viewModelScope.launch(Dispatchers.IO) {
//            for (i in 1..3){ //каждые 3 секунды сетается новое значение в livedata
//                delay(2000)
//                liveData.postValue(AppState.Success(WEa)
//            }
//        }
    }

    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)

    fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveData.value = AppState.Error()
        Thread {
            sleep(1000)
            liveData.postValue(
                AppState.Success(
                    if (isRussian) repo.getWeatherFromLocalStorageRus()
                    else repo.getWeatherFromLocalStorageWorld()
                )
            )
        }.start()
    }
}
