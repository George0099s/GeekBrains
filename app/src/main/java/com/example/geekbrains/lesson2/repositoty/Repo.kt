package com.example.geekbrains.lesson2.repositoty

import com.example.geekbrains.Weather
import com.example.geekbrains.WeatherDTO
import retrofit2.Call

interface Repo {
    fun getWeatherFromServer(lat: Double, lon: Double): Call<WeatherDTO>
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}
