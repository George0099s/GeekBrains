package com.example.geekbrains

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

enum class WeatherType {
    SUNNY,
    RAINY,
    CLOUDY
}

class MainActivity : AppCompatActivity() {

    val weather = Weather()

    companion object Factory {
        private val EXTRA_WEATHER = MainActivity::class.java

        fun getIntent(ctx: Context, weather: Weather): Intent {
            val intent = Intent(ctx, MainActivity::class.java)
//            intent.putExtra(EXTRA_WEATHER, weather)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val noteED: EditText = findViewById(R.id.note_ed)

        val town = if (noteED != null){
            noteED.text
        } else "no data"

        supportActionBar?.title = if (noteED != null) "title1" else "title2"

        for (weather in Repository.getWeatherList())
            print(weather)

        for (i in 0..10)
            print(i)

        for (i in 10 downTo 1 step 2)
            print(i)

        for (i in 0 until Repository.getWeatherList().size)
            print(i)

    }

    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            TODO("Not yet implemented")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            TODO("Not yet implemented")
        }

        override fun afterTextChanged(s: Editable?) {
            TODO("Not yet implemented")
        }
    }

    fun setWeather(town: String, isHomeTown: Boolean = true, temperature: Int = 15) {

    }

    val color = when
        (weather.type) {
        WeatherType.SUNNY -> Color.RED
        WeatherType.RAINY -> {
        }
        WeatherType.CLOUDY -> TODO()
    }
}