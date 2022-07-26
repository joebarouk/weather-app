package com.example.weatherapp.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.city.CityListAdapter
import com.example.weatherapp.city.CityViewModel
import com.example.weatherapp.city.OnClickListener
import com.example.weatherapp.city.OverviewFragmentDirections
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentWeatherBinding.inflate(inflater)



        binding.lifecycleOwner = this

        val cityProperty = WeatherFragmentArgs.fromBundle(requireArguments()).selectedCity
        val viewModelFactory = WeatherViewModelFactory(cityProperty, application)

        viewModel = ViewModelProvider(this,viewModelFactory).get(WeatherViewModel::class.java)

        binding.viewModel = viewModel

        binding.gridList.adapter = WeatherListAdapter()


        binding.seemoreButton.setOnClickListener {
            viewModel.displayHistoryDetails(cityProperty)
        }

        viewModel.navigateToSelectedCity.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(WeatherFragmentDirections.actionWeatherFragmentToHistoryFragment(it))
                viewModel.displayHistoryDetailsComplete()
            }
        })


        return binding.root
    }

}