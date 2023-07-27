package com.example.chapter11.practice.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter11.databinding.ItemLabTimeBinding
import com.example.chapter11.practice.model.LabTimeItems

class StopWatchAdapter() : ListAdapter<LabTimeItems, StopWatchAdapter.StopWatchViewHolder>(
    object : DiffUtil.ItemCallback<LabTimeItems>() {
        override fun areItemsTheSame(oldItem: LabTimeItems, newItem: LabTimeItems): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: LabTimeItems, newItem: LabTimeItems): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopWatchViewHolder {
        return StopWatchViewHolder(ItemLabTimeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: StopWatchViewHolder, position: Int) {
        Log.d("로그", "onBindViewHolder ${currentList}")
        holder.bind(currentList[position])
    }

    inner class StopWatchViewHolder(private val binding: ItemLabTimeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: LabTimeItems){
            binding.labNameTextView.text = "랩 ${item.id}"
            binding.labTimeTextView.text = item.duration
        }
    }
}

