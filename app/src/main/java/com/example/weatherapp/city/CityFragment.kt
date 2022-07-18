package com.example.weatherapp.city

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityBinding



class OverviewFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: CityViewModel by lazy {
        ViewModelProvider(this).get(CityViewModel::class.java)
    }

    lateinit var binding: FragmentCityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentCityBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel


        binding.enterButton.setOnClickListener{
            onType()
        }


        binding.cityList.adapter = CityListAdapter(OnClickListener {
            viewModel.displayCityDetails(it)
        })

        viewModel.navigateToSelectedCity.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(OverviewFragmentDirections.actionCityFragmentToWeatherFragment(it))
                viewModel.displayCityDetailsComplete()
            }
        })


        return binding.root
    }


    fun onType(){
        viewModel.getCities(binding.enterCity.text.toString())
    }
}