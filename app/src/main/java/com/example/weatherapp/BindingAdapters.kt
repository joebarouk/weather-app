package com.example.weatherapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherapp.city.CityApiStatus
import com.example.weatherapp.city.CityListAdapter
import com.example.weatherapp.database.History
import com.example.weatherapp.history.HistoryListAdapter
import com.example.weatherapp.network.City
import com.example.weatherapp.network.Grid
import com.example.weatherapp.weather.WeatherListAdapter
import com.example.weatherapp.weather.WeatherViewModel


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<City>?) {
    val adapter = recyclerView.adapter as CityListAdapter
    adapter.submitList(data)
}

@BindingAdapter("listWeather")
fun bindGridRecyclerView(recyclerView: RecyclerView, data: List<Grid>?) {
    val adapter = recyclerView.adapter as WeatherListAdapter
    adapter.submitList(data)
}

@BindingAdapter("listHistory")
fun bindHistoryRecyclerView(recyclerView: RecyclerView, data: List<History>?) {
    val adapter = recyclerView.adapter as HistoryListAdapter
    adapter.submitList(data)
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


@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: Grid?) {
    item?.let {
        setImageResource(
            when (item.property) {
                "Sunrise" -> R.drawable.ic_weather_sun
                "Sunset" -> R.drawable.ic_weather_moon2
                "Wind" -> R.drawable.ic_weather_air
                "Pressure" -> R.drawable.ic_weather_pressure
                "Humidity" -> R.drawable.ic_weather_humidity
                "Created By" -> R.drawable.ic_weather_info
                else -> R.drawable.loading_img
            }
        )
    }
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
