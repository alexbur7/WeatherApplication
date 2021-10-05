package com.example.myapplication.presentation.storageweather

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentStorageWeatherBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.presentation.main.appComponent
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import kotlinx.serialization.ExperimentalSerializationApi

class StorageWeatherFragment : Fragment(R.layout.fragment_storage_weather) {

    private val viewBinding by viewBinding(FragmentStorageWeatherBinding::bind)
    private var callback: Callback? = null

    @ExperimentalSerializationApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
        callback = context as Callback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weathers = mutableListOf<WeatherEntity>()
        val weathersList: List<WeatherEntity>? =
            arguments?.getParcelableArrayList(WEATHERS_KEY)
        if (weathersList != null) {
            weathers.addAll(weathersList)
        }
        with(viewBinding) {
            openFindWeather.setOnClickListener {
                callback?.openFindWeatherFragment(weathers)
            }
            weathersRecView.run {
                adapter = WeatherAdapter(weathers)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    companion object {
        fun newInstance(weathers: List<WeatherEntity>): StorageWeatherFragment {
            val args = bundleOf(WEATHERS_KEY to weathers)
            val fragment = StorageWeatherFragment()
            fragment.arguments = args
            return fragment
        }

        private const val WEATHERS_KEY = "weathers_key"
    }

    interface Callback {
        fun openFindWeatherFragment(weathers: List<WeatherEntity>)
    }
}