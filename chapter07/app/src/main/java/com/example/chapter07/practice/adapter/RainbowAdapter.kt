package com.example.chapter07.practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chapter07.R
import com.example.chapter07.databinding.ItemRainbowBinding
import com.example.chapter07.practice.fragment.ColorItem
import com.example.chapter07.practice.fragment.RainbowFragment

class RainbowAdapter(
    private val rainbowList: List<ColorItem>
): RecyclerView.Adapter<RainbowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RainbowViewHolder {
        return RainbowViewHolder(ItemRainbowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RainbowViewHolder, position: Int) {
        holder.bind(rainbowList[position])
    }

    override fun getItemCount(): Int = rainbowList.size
}

class RainbowViewHolder(val binding: ItemRainbowBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(colorItem: ColorItem){
        binding.rainbowBackground.setBackgroundResource(colorItem.color)
    }
}
