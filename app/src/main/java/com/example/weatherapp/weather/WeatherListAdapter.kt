package com.example.weatherapp.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.WeatherViewItemBinding
import com.example.weatherapp.network.City
import com.example.weatherapp.network.Grid

class WeatherListAdapter() :
    ListAdapter<Grid, WeatherListAdapter.GridListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Grid>() {
        override fun areItemsTheSame(oldItem: Grid, newItem: Grid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Grid, newItem: Grid): Boolean {
            return oldItem.property == newItem.property
        }
    }

    class GridListViewHolder(private var binding: WeatherViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(grid: Grid) {
            binding.grid = grid
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridListViewHolder {
        return GridListViewHolder(WeatherViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: GridListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    }
