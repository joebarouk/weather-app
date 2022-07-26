package com.example.weatherapp.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.database.WeatherDatabase
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.weather.WeatherFragmentArgs
import com.example.weatherapp.weather.WeatherListAdapter
import com.example.weatherapp.weather.WeatherViewModel
import com.example.weatherapp.weather.WeatherViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentHistoryBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val dataSource = WeatherDatabase.getInstance(application).weatherDatabaseDao

        val cityProperty = HistoryFragmentArgs.fromBundle(requireArguments()).selectedCity
        val viewModelFactory = HistoryViewModelFactory(cityProperty, dataSource)

        viewModel = ViewModelProvider(
            this, viewModelFactory).get(HistoryViewModel::class.java)

        binding.viewModel = viewModel

      binding.historyList.adapter = HistoryListAdapter()



        return binding.root
    }



}