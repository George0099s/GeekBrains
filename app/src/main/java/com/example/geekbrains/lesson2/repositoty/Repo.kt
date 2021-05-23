package com.example.geekbrains.lesson2.repositoty

import com.example.geekbrains.lesson2.Weather

interface Repo {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}