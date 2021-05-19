package com.example.geekbrains

object Repository {
    private var weatherList = listOf(Weather("Москва", 15))

    fun getWeatherList(): List<Weather> {
        return weatherList
    }
}