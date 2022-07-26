package com.example.weatherapp.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.database.History
import com.example.weatherapp.databinding.HistoryViewItemBinding

 class HistoryListAdapter():
 ListAdapter<History, HistoryListAdapter.HistoryListViewHolder>(DiffCallback) {

 companion object DiffCallback : DiffUtil.ItemCallback<History>() {
 override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
 return oldItem === newItem
 }

 override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
 return oldItem.date == newItem.date
 }
 }

 class HistoryListViewHolder(private var binding: HistoryViewItemBinding) :
 RecyclerView.ViewHolder(binding.root) {
 fun bind(history: History) {
 binding.history = history
 binding.executePendingBindings()
 }
 }

 override fun onCreateViewHolder(
 parent: ViewGroup,
 viewType: Int
 ): HistoryListViewHolder {
 return HistoryListViewHolder(
 HistoryViewItemBinding.inflate(
 LayoutInflater.from(
 parent.context
 ), parent, false
 )
 )
 }


 override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
 holder.bind(getItem(position))
 }
 }