package com.example.geekbrains.lesson2

data class City(
    val city: String,
    val lat: Double,
    val lon: Double
)

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 15,
    val feelsLike: Int = 13
)

fun getDefaultCity() = City("Москва", 55.755826, 37.617299900000035)