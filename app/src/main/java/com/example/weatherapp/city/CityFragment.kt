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


        binding.enterCity.setOnClickListener{
            onType()
        }
/*
        viewModel.get_city_from_text.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                viewModel.getCities(it)
            }
        })

*/

        binding.cityList.adapter = CityListAdapter(OnClickListener {

        })

        return binding.root
    }


    fun onType(){
        viewModel.getCities(binding.enterCity.text.toString())
    }
}