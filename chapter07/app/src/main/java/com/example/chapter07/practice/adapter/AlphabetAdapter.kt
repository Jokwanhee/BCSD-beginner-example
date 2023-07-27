package com.example.chapter07.practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter07.databinding.ItemNumberBinding
import com.example.chapter07.practice.fragment.AlphabetItem

class AlphabetAdapter(
    val alphabetList: List<AlphabetItem>
): RecyclerView.Adapter<AlphabetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetViewHolder {
        return AlphabetViewHolder(ItemNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AlphabetViewHolder, position: Int) {
        holder.bind(alphabetList[position])
    }

    override fun getItemCount(): Int = alphabetList.size
}

class AlphabetViewHolder(val binding: ItemNumberBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(item: AlphabetItem){
        binding.numberNumText.text = item.alphabet
    }
}