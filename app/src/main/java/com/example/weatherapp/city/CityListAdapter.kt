package com.example.weatherapp.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.CityViewItemBinding
import com.example.weatherapp.network.City


class CityListAdapter( val clickListener: OnClickListener ) :
    ListAdapter<City, CityListAdapter.CityListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CityListViewHolder(private var binding: CityViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: OnClickListener, city: City) {
            binding.city = city
            binding.clickListener = listener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityListViewHolder {
        return CityListViewHolder(CityViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        holder.bind(clickListener,getItem(position))
    }

    }
class OnClickListener(val clickListener: (city:City) -> Unit) {
    fun onClick(city:City) = clickListener(city)
}

