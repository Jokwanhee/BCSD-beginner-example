package com.example.chapter12.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter12.databinding.ItemWordBinding
import com.example.chapter12.db.Word

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
        // onClick() 인자로 currentList[position] 을 넣으면 기존에 position 이 변경되어 클릭 시 position 오류가 발생한다.
        // 그러므로 word 라는 객체로 currentList[position] 을 담아서 기억해서 진행하는 것이 올바르다.
        holder.itemView.setOnClickListener { context.onClick(word) }
    }

    inner class WordViewHolder(
        private val binding: ItemWordBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word){
            binding.textTextView.text = item.text
            binding.meanTextView.text = item.mean
        }
    }

    interface ItemClickListener {
        fun onClick(word: Word)
    }
}