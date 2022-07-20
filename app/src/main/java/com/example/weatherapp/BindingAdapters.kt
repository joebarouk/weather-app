package com.example.weatherapp

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.city.CityApiStatus
import com.example.weatherapp.city.CityListAdapter
import com.example.weatherapp.network.City
import com.example.weatherapp.network.Grid


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<City>?) {
    val adapter = recyclerView.adapter as CityListAdapter
    adapter.submitList(data)
}

@BindingAdapter("listWeather")
fun bindGridRecyclerView(recyclerView: RecyclerView, data: List<Grid>?) {
   // val adapter = recyclerView.adapter as CityListAdapter
  //  adapter.submitList(data)
}

@BindingAdapter("cityApiStatus")
fun bindStatus(statusImageView: ImageView, status: CityApiStatus?) {
    when (status) {
        CityApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CityApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        CityApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}