package com.example.myapplication.presentation.storageweather

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStorageWeatherBinding
import com.example.myapplication.presentation.base.appComponent
import com.example.myapplication.presentation.findweather.FindWeatherFragment
import com.example.myapplication.presentation.main.appComponent
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import kotlinx.serialization.ExperimentalSerializationApi

class StorageWeatherFragment : Fragment(R.layout.fragment_storage_weather) {

    private val viewBinding by viewBinding(FragmentStorageWeatherBinding::bind)
    private val weathers = arrayListOf<WeatherEntity>()

    @ExperimentalSerializationApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weathersList: ArrayList<WeatherEntity>? =
            arguments?.getParcelableArrayList(WEATHERS_KEY)
        if (weathersList != null) {
            weathers.addAll(weathersList)
        }
        with(viewBinding) {
            openFindWeather.setOnClickListener {
                openFindWeatherFragment(weathers)
            }
            weathersRecView.run {
                adapter = WeatherAdapter().apply {
                    setData(weathers)
                }
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun openFindWeatherFragment(weathers: ArrayList<WeatherEntity>) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FindWeatherFragment.newInstance(weathers))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(weathers: ArrayList<WeatherEntity>): StorageWeatherFragment {
            val args = Bundle()
            args.putParcelableArrayList(WEATHERS_KEY, weathers)
            val fragment = StorageWeatherFragment()
            fragment.arguments = args
            return fragment
        }

        private const val WEATHERS_KEY = "weathers_key"
    }
}