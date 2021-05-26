package com.example.geekbrains.lesson3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geekbrains.R
import com.example.geekbrains.Weather
import com.example.geekbrains.databinding.FragmentLesson3DetailsBinding


class Lesson3Details : Fragment() {

    private var _binding: FragmentLesson3DetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLesson3DetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
        if (weather != null) {
            val city = weather.city
            binding.city.text = city.city
            binding.latlon.text = "${city.lat} ${city.lon}"
            binding.feelsLike.text = weather.temperature.toString()
        }
    }

    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): Lesson3Details {
            val fragment = Lesson3Details()
            fragment.arguments = bundle
            return fragment
        }
    }
}
