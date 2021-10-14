package com.example.myapplication.presentation.findweather

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFindWeatherBinding
import com.example.myapplication.domain.entity.WeatherEntity
import com.example.myapplication.presentation.main.appComponent
import com.example.myapplication.presentation.storageweather.StorageWeatherFragment
import com.example.myapplication.presentation.utils.extentions.showToastWithErrorMessage
import com.example.myapplication.presentation.utils.factory.ViewModelFactory
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class FindWeatherFragment : Fragment(R.layout.fragment_find_weather) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewBinding by viewBinding(FragmentFindWeatherBinding::bind)
    private val viewModel: FindWeatherViewModel by viewModels { viewModelFactory }

    @ExperimentalSerializationApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            viewModel.weatherLiveData.observe(viewLifecycleOwner) {
                nameCity.setText(it.nameCity)
                nameCity.setSelection(it.nameCity.length)
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
            }

            viewModel.errorTextIdLiveData.observe(viewLifecycleOwner) {
                requireContext().showToastWithErrorMessage(it)
            }

            findWeather.setOnClickListener {
                viewModel.findWeather(nameCity.text.toString())
            }
        }
    }

    companion object {
        fun newInstance(): FindWeatherFragment {
            val args = Bundle()
            val fragment = FindWeatherFragment()
            fragment.arguments = args
            return fragment
        }
    }
}