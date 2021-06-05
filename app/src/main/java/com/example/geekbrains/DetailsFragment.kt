package com.example.geekbrains

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.geekbrains.databinding.FragmentDetailsBinding
import com.example.geekbrains.lesson2.repositoty.RepoImpl
import retrofit2.Call
import retrofit2.Response

val WEATHER_API_KEY = "9ca62c0b-426d-4b6a-a1a3-f9c5ffda007f"

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_INTENT_EMPTY_EXTRA = "INTENT IS EMPTY"
const val DETAILS_DATA_EMPTY_EXTRA = "DATA IS EMPTY"
const val DETAILS_RESPONSE_EMPTY_EXTRA = "RESPONSE IS EMPTY"
const val DETAILS_REQUEST_ERROR_EXTRA = "REQUEST ERROR"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "REQUEST ERROR MESSAGE"
const val DETAILS_URL_MALFORMED_EXTRA = "URL MALFORMED"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val DETAILS_TEMP_EXTRA = "TEMPERATURE"
const val DETAILS_FEELS_LIKE_EXTRA = "FEELS LIKE"
const val DETAILS_CONDITION_EXTRA = "CONDITION"
private const val TEMP_INVALID = -100
private const val FEELS_LIKE_INVALID = -100
private const val PROCESS_ERROR = "Обработка ошибки"

class Lesson3Details : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val TAG = "Lesson3Details"
    val broadcastIntent = Intent(DETAILS_INTENT_FILTER)

    private fun putLoadResult(result: String) {
        broadcastIntent.putExtra(DETAILS_LOAD_RESULT_EXTRA, result)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        binding.mainView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        with(weatherBundle.city) {
            RepoImpl().getWeatherFromServer(lat, lon)
                .enqueue(object : retrofit2.Callback<WeatherDTO> {
                    override fun onResponse(
                        call: Call<WeatherDTO>,
                        response: Response<WeatherDTO>
                    ) {
                        val dto = response.body()?.fact
                        displayWeather(response.body())
//                        putLoadResult(DETAILS_RESPONSE_SUCCESS_EXTRA)
//                        broadcastIntent.putExtra(DETAILS_TEMP_EXTRA, dto?.temp)
//                        broadcastIntent.putExtra(DETAILS_FEELS_LIKE_EXTRA, dto?.feels_like)
//                        broadcastIntent.putExtra(DETAILS_CONDITION_EXTRA, dto?.condition)
//                        LocalBroadcastManager.getInstance(requireContext())
//                            .sendBroadcast(broadcastIntent)
                    }

                    override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        Toast.makeText(requireContext(), "$t", Toast.LENGTH_SHORT).show()
                    }

                })
        }

        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(loadResultsReceiver, IntentFilter(DETAILS_INTENT_FILTER))
    }

    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
//                DETAILS_INTENT_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_DATA_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_RESPONSE_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_REQUEST_ERROR_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_SUCCESS_EXTRA -> displayWeather(
                    WeatherDTO(
                        FactDTO(
                            intent.getIntExtra(
                                DETAILS_TEMP_EXTRA, TEMP_INVALID
                            ),
                            intent.getIntExtra(DETAILS_FEELS_LIKE_EXTRA, FEELS_LIKE_INVALID),
                            intent.getStringExtra(
                                DETAILS_CONDITION_EXTRA
                            )
                        )
                    )
                )
                else -> TODO(PROCESS_ERROR)
            }
        }
    }


    private fun displayWeather(weatherDTO: WeatherDTO?) {
        with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            val city = weatherBundle.city
            cityName.text = city.city
            cityCoordinates.text =
                "${city.lat}, ${city.lon}"
            weatherCondition.text = weatherDTO?.fact?.condition
            temperatureValue.text = weatherDTO?.fact?.temp.toString()
            feelsLikeValue.text = weatherDTO?.fact?.feels_like.toString()
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
