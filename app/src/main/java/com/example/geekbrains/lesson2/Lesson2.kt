package com.example.geekbrains.lesson2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.geekbrains.R
import com.example.geekbrains.databinding.Lesson2FragmentBinding

class Lesson2 : Fragment() {

    companion object {
        fun newInstance() = Lesson2()
        private const val TAG = "Lesson2"
    }

    private lateinit var viewModel: Lesson2ViewModel

    private var _binding: Lesson2FragmentBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Lesson2FragmentBinding.inflate(inflater, container, false)
        binding?.btnClick?.setOnClickListener {
            viewModel.getWeatherFromLocalSource()
        }
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Lesson2ViewModel::class.java)
        val observer = Observer<AppState> { state -> render(state) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success -> {
                binding?.tv1?.text = state.weatherData.city.toString()
                binding?.tv2?.text = state.weatherData.feelsLike.toString()
                binding?.tv3?.text = state.weatherData.temperature.toString()
                binding?.pb?.visibility = View.GONE
            }
            is AppState.Error -> {
                binding?.tv3?.text = "error"
                binding?.pb?.visibility = View.GONE
            }
            AppState.Loading -> {
                binding?.pb?.visibility = View.VISIBLE
            }
        }

    }

    fun setText(str: String = "Text 1") {
        binding?.tv1?.text = str
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}