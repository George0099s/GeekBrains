package com.example.geekbrains.lesson3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.geekbrains.Lesson3Details
import com.example.geekbrains.R
import com.example.geekbrains.Weather
import com.example.geekbrains.databinding.Lesson3FramentFragmentBinding
import com.example.geekbrains.lesson2.AppState
import com.example.geekbrains.Lesson2ViewModel
import com.example.geekbrains.lesson4.replaceWithBackStack
import com.example.geekbrains.lesson4.showSnackBar
import com.google.android.material.snackbar.Snackbar

class Lesson3Fragment : Fragment(), ActionListener {

    private var _binding: Lesson3FramentFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: Lesson2ViewModel by lazy {
        ViewModelProvider(this).get(Lesson2ViewModel::class.java)
    }

    private val adapter = MainFragmentAdapter(this)
    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Lesson3FramentFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }

        "sss".also(::print)
        viewModel.getData().observe(viewLifecycleOwner) {

            renderData(it)
        }
        viewModel.getWeatherFromLocalSourceRus()
    }

    private fun changeWeatherDataSet() {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocalSourceWorld()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_launcher_background)
        } else {
            viewModel.getWeatherFromLocalSourceRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_launcher_foreground)
        }
        isDataSetRus = !isDataSetRus
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(appState.weatherData)
            }
            is AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                binding.mainFragmentFAB.showSnackBar(
                    "Error",
                    "Reload",
                    { viewModel.getWeatherFromLocalSourceRus() },
                    Snackbar.LENGTH_INDEFINITE
                )
            }
        }
    }
    companion object {
        fun newInstance() =
            Lesson3Fragment()
    }

    override fun action(weather: Weather) {
        replaceWithBackStack(
            R.id.cont,
            Lesson3Details.newInstance(Bundle().apply {
                putParcelable(Lesson3Details.BUNDLE_EXTRA, weather)
            })
        )
    }
}
