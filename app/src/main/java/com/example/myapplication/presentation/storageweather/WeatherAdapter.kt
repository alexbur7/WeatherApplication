package com.example.myapplication.presentation.storageweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemWeatherBinding
import com.example.myapplication.presentation.findweather.entity.WeatherEntity

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    private val weathers = mutableListOf<WeatherEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.onBind(weathers[position])
    }

    override fun getItemCount(): Int = weathers.size

    fun setData(weathers: List<WeatherEntity>) {
        this.weathers.clear()
        this.weathers.addAll(weathers)
    }

    inner class WeatherHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val viewBinding by viewBinding(ItemWeatherBinding::bind)

        fun onBind(weatherEntity: WeatherEntity) {
            with(viewBinding) {
                nameCity.text = weatherEntity.nameCity
                weatherText.text = root.context.getString(
                    R.string.temperature,
                    weatherEntity.weatherInfo.temperature.toString()
                )
            }
        }
    }
}