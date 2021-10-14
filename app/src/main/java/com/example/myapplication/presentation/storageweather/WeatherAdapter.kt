package com.example.myapplication.presentation.storageweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemWeatherBinding
import com.example.myapplication.domain.entity.WeatherEntity

class WeatherAdapter(
    private val repeatWeather: (String) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    private val diffUtil = AsyncListDiffer(this, WeatherCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.onBind(diffUtil.currentList[position])
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    fun setData(weathers: List<WeatherEntity>) {
        diffUtil.submitList(weathers)
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
                repeatButton.setOnClickListener {
                    repeatWeather(weatherEntity.nameCity)
                }
            }
        }
    }
}

class WeatherCallback : DiffUtil.ItemCallback<WeatherEntity>() {
    override fun areItemsTheSame(oldItem: WeatherEntity, newItem: WeatherEntity): Boolean {
        return oldItem.nameCity == newItem.nameCity
    }

    override fun areContentsTheSame(oldItem: WeatherEntity, newItem: WeatherEntity): Boolean {
        return oldItem == newItem
    }

}