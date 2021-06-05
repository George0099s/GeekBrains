package com.example.geekbrains.base

import com.example.geekbrains.Weather

class MainViewState(val weatherList: List<Weather>){

    val hasData: Boolean
    get() = weatherList.size != 0
}