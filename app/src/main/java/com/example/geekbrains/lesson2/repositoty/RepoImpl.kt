package com.example.geekbrains.lesson2.repositoty

import com.example.geekbrains.lesson2.Weather

class RepoImpl: Repo{
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather()
    }

}