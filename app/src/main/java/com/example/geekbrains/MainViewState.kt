package com.example.geekbrains

class MainViewState(val weatherList: List<Weather>){

    val hasData: Boolean
    get() = weatherList.size != 0
}