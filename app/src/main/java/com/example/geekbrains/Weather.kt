package com.example.geekbrains

import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Weather(var town: String = "Москва", var temperature: Int = 15, var type: WeatherType = WeatherType.CLOUDY){

    companion object : Comparator<Weather> {
        override fun compare(o1: Weather, o2: Weather): Int {
            if (o1.temperature > o2.temperature)
                return 1
            else if (o1.temperature == o2.temperature)
                return  0
            else
                return -1
        }
    }
}