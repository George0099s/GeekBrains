package com.example.geekbrains.data

import com.example.geekbrains.WEATHER_API_KEY
import com.example.geekbrains.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Service {

    @GET("v2/forecast?extra=true")
    fun getWeather(
        @Header("X-Yandex-API-Key") key: String = WEATHER_API_KEY,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<WeatherDTO>
}