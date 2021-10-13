package com.example.myapplication.presentation.storageweather

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStorageWeatherBinding
import com.example.myapplication.presentation.findweather.FindWeatherFragment
import com.example.myapplication.presentation.main.appComponent
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import com.example.myapplication.presentation.main.Callback
import com.example.myapplication.presentation.main.MainActivity
import kotlinx.serialization.ExperimentalSerializationApi

class StorageWeatherFragment : Fragment(R.layout.fragment_storage_weather) {

    private val viewBinding by viewBinding(FragmentStorageWeatherBinding::bind)
    private var weathersList: ArrayList<WeatherEntity>? = null
    private val weatherAdapter by lazy(LazyThreadSafetyMode.NONE) { WeatherAdapter() }
    private var callback: Callback? = null

    @ExperimentalSerializationApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
        if (context is Callback) {
            callback = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weathersList = if (savedInstanceState != null) {
            savedInstanceState.getParcelableArrayList(WEATHERS_KEY)
        } else {
            arguments?.getParcelableArrayList(WEATHERS_KEY)
        }
        with(viewBinding) {
            toolbar.inflateMenu(R.menu.menu)
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.deleteWeathers -> deleteWeathers()
                }
                return@setOnMenuItemClickListener true
            }

            openFindWeather.setOnClickListener {
                openFindWeatherFragment(weathersList ?: arrayListOf())
            }
            weathersRecView.run {
                adapter = weatherAdapter.apply {
                    setData(weathersList?.toList() ?: arrayListOf())
                }
                layoutManager = LinearLayoutManager(context)
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(WEATHERS_KEY, weathersList)
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    private fun deleteWeathers() {
        weathersList?.let {
            it.clear()
            weatherAdapter.setData(it)
        }
    }

    private fun openFindWeatherFragment(weathers: ArrayList<WeatherEntity>) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FindWeatherFragment.newInstance(weathers))
            .addToBackStack(null)
            .commit()
        callback?.changeScreen()
    }

    companion object {
        fun newInstance(weathers: ArrayList<WeatherEntity>? = null): StorageWeatherFragment {
            val args = Bundle()
            args.putParcelableArrayList(WEATHERS_KEY, weathers)
            val fragment = StorageWeatherFragment()
            fragment.arguments = args
            return fragment
        }

        private const val WEATHERS_KEY = "weathers_key"
    }
}