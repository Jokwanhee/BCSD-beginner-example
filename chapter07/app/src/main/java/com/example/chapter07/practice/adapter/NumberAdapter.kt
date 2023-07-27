package com.example.chapter07.practice.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter07.databinding.ItemNumberBinding
import com.example.chapter07.practice.fragment.NumberItem

class NumberAdapter(
    private val numberList: List<NumberItem>
): RecyclerView.Adapter<NumberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        Log.d("로그", "NumberAdapter onCreateViewHolder()")
        return NumberViewHolder(ItemNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(numberList[position])
    }

    override fun getItemCount(): Int = numberList.size
}

class NumberViewHolder(private val binding: ItemNumberBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(item: NumberItem){
        binding.numberNumText.text = item.number
    }
}