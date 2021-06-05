package com.example.geekbrains.lesson2

import com.example.geekbrains.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable? = null) : AppState()
    object Loading : AppState()
}