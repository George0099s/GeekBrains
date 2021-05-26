package com.example.geekbrains.lesson2.repositoty

import com.example.geekbrains.Weather
import com.example.geekbrains.getRussianCities
import com.example.geekbrains.getWorldCities

class RepoImpl: Repo{
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }
}