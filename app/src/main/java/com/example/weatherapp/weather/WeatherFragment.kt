package com.example.weatherapp.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.city.CityViewModel
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentWeatherBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val cityProperty = WeatherFragmentArgs.fromBundle(requireArguments()).selectedCity
        val viewModelFactory = WeatherViewModelFactory(cityProperty, application)

        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(WeatherViewModel::class.java)

        return binding.root
    }

}