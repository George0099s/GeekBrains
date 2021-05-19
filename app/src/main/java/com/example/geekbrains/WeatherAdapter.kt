package com.example.geekbrains

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter : RecyclerView.Adapter<WeatherViewHolder>() {
    var items: List<Weather> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}