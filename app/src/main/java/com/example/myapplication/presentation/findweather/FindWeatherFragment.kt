package com.example.myapplication.presentation.findweather

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFindWeatherBinding
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import com.example.myapplication.presentation.main.appComponent
import com.example.myapplication.presentation.storageweather.StorageWeatherFragment
import com.example.myapplication.presentation.utils.ErrorHandler
import com.example.myapplication.presentation.utils.factory.ViewModelFactory
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class FindWeatherFragment : Fragment(R.layout.fragment_find_weather) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var errorHandler: ErrorHandler

    private val viewBinding by viewBinding(FragmentFindWeatherBinding::bind)
    private val viewModel: FindWeatherViewModel by viewModels { viewModelFactory }
    private val weathers = arrayListOf<WeatherEntity>()


    @ExperimentalSerializationApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
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
                weathers.addWeather(it)
            }

            viewModel.errorLiveData.observe(viewLifecycleOwner) {
                errorHandler.showErrorToast(it)
            }

            openStorageWeather.setOnClickListener {
                openStorageWeatherFragment(weathers)
            }

            findWeather.setOnClickListener {
                viewModel.findWeather(nameCity.text.toString())
            }
        }
    }

    private fun ArrayList<WeatherEntity>.addWeather(weatherEntity: WeatherEntity) {
        if (find { it.nameCity == weatherEntity.nameCity } == null) {
            add(weatherEntity)
        }
    }

    private fun openStorageWeatherFragment(weathers: ArrayList<WeatherEntity>) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, StorageWeatherFragment.newInstance(weathers))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(weathers: ArrayList<WeatherEntity>? = null): FindWeatherFragment {
            val args = Bundle()
            args.putParcelableArrayList(WEATHER_LIST_KEY, weathers)
            val fragment = FindWeatherFragment()
            fragment.arguments = args
            return fragment
        }

        private const val WEATHER_LIST_KEY = "weather_list_key"
    }
}