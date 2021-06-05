package com.example.geekbrains.lesson2.repositoty

import RetrofitClient
import com.example.geekbrains.getRussianCities
import com.example.geekbrains.getWorldCities

class RepoImpl : Repo {

    override fun getWeatherFromServer(lat: Double, lon: Double) =
        RetrofitClient.getClient().getWeather(lat = lat, lon = lon)

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()

}