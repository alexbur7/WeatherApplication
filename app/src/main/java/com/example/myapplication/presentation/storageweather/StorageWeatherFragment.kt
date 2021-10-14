package com.example.myapplication.presentation.storageweather

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentStorageWeatherBinding
import com.example.myapplication.presentation.findweather.FindWeatherFragment
import com.example.myapplication.domain.entity.WeatherEntity
import com.example.myapplication.presentation.main.appComponent
import com.example.myapplication.presentation.utils.extentions.showToastWithErrorMessage
import com.example.myapplication.presentation.utils.factory.ViewModelFactory
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class StorageWeatherFragment : Fragment(R.layout.fragment_storage_weather) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewBinding by viewBinding(FragmentStorageWeatherBinding::bind)
    private val weatherAdapter by lazy(LazyThreadSafetyMode.NONE) { WeatherAdapter() }
    private val viewModel by viewModels<StorageWeatherViewModel> { factory }

    @ExperimentalSerializationApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            toolbar.inflateMenu(R.menu.menu)
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.deleteWeathers -> deleteWeathers()
                }
                return@setOnMenuItemClickListener true
            }

            viewModel.weathersLiveData.observe(viewLifecycleOwner) {
                weatherAdapter.setData(it)
            }

            viewModel.errorTextIdLiveData.observe(viewLifecycleOwner) {
                requireContext().showToastWithErrorMessage(it)
            }

            openFindWeather.setOnClickListener {
                openFindWeatherFragment()
            }
            weathersRecView.run {
                adapter = weatherAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun deleteWeathers() {
        viewModel.deleteWeathers()
    }

    private fun openFindWeatherFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FindWeatherFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(): StorageWeatherFragment {
            val args = Bundle()
            val fragment = StorageWeatherFragment()
            fragment.arguments = args
            return fragment
        }
    }
}