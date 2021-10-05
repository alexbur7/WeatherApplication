package com.example.myapplication.presentation.findweather

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFindWeatherBinding

import com.example.myapplication.presentation.utils.factory.ViewModelFactory
import com.example.myapplication.presentation.main.appComponent
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class FindWeatherFragment : Fragment(R.layout.fragment_find_weather) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewBinding by viewBinding(FragmentFindWeatherBinding::bind)
    private val viewModel: FindWeatherViewModel by viewModels { viewModelFactory }
    private val weathers = mutableSetOf<WeatherEntity>()
    private var callback: Callback? = null


    @ExperimentalSerializationApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
        callback = context as Callback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weathersList: List<WeatherEntity>? = arguments?.getParcelableArrayList(WEATHER_LIST_KEY)
        if (weathersList != null) {
            weathers.addAll(weathersList)
        }
        with(viewBinding) {
            viewModel.weatherLiveData.observe(viewLifecycleOwner) {
                temperature.text = requireContext().getString(
                    R.string.temperature,
                    it.weatherInfo.temperature.toString()
                )
                minTemperature.text = requireContext().getString(
                    R.string.min_temperature,
                    it.weatherInfo.minTemperature.toString()
                )
                maxTemperature.text = requireContext().getString(
                    R.string.max_temperature,
                    it.weatherInfo.maxTemperature.toString()
                )
                speedWind.text =
                    requireContext().getString(R.string.speed_wind, it.wind.speed.toString())
                weathers.add(it)
            }

            openStorageWeather.setOnClickListener {
                callback?.openStorageWeatherFragment(weathers.toList())
            }

            findWeather.setOnClickListener {
                viewModel.findWeather(nameCity.text.toString())
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    interface Callback {
        fun openStorageWeatherFragment(weathers: List<WeatherEntity>)
    }

    companion object {
        fun newInstance(weathers: List<WeatherEntity>? = null): FindWeatherFragment {
            val args = bundleOf(WEATHER_LIST_KEY to weathers)
            val fragment = FindWeatherFragment()
            fragment.arguments = args
            return fragment
        }

        private const val WEATHER_LIST_KEY = "weather_list_key"
    }
}