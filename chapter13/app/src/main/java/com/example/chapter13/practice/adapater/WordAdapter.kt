package com.example.chapter13.practice.adapater

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chapter13.databinding.ItemWordBinding
import com.example.chapter13.practice.db.Word

class WordAdapter(
    private val context: ItemClickListener
): ListAdapter<Word, WordAdapter.WordViewHolder>(
    object:DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = currentList[position]
        holder.bind(word)
        holder.itemView.setOnClickListener {
            context.onClick(word)
        }
    }

    inner class WordViewHolder(
        private val binding: ItemWordBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word){
            binding.textTextView.text = item.text
            binding.meanTextView.text = item.mean
            Glide.with(binding.root)
                .load(item.image)
                .into(binding.imageView)
        }
    }

    interface ItemClickListener {
        fun onClick(word: Word)
    }
}