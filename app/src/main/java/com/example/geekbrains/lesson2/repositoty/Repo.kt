package com.example.geekbrains.lesson2.repositoty

import com.example.geekbrains.Weather

interface Repo {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}
