package com.example.chapter10.practice.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chapter10.databinding.ItemMusicBinding
import com.example.chapter10.practice.model.MusicItem
import com.example.chapter10.practice.utils.setTime
import java.text.SimpleDateFormat

class PracticeAdapter(
    private val musicList: List<MusicItem>,
    val context: Context
):RecyclerView.Adapter<PracticeAdapter.PracticeViewHolder>() {
    private lateinit var itemStartClickListener: ItemOnClickListener
    private lateinit var itemPauseClickListener: ItemOnClickListener
    private lateinit var itemStopClickListener: ItemOnClickListener

    fun onStartClickListener(listener: ItemOnClickListener){
        itemStartClickListener = listener
    }
    fun onPauseClickListener(listener: ItemOnClickListener){
        itemPauseClickListener = listener
    }
    fun onStopClickListener(listener: ItemOnClickListener){
        itemStopClickListener = listener
    }

    interface ItemOnClickListener{
        fun onStartMusic(music:MusicItem)
        fun onPauseMusic(music:MusicItem)
        fun onStopMusic(music:MusicItem)
    }

    inner class PracticeViewHolder(private val binding: ItemMusicBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:MusicItem){
            binding.titleTextView.text = item.musicTitle
            binding.timeTextView.text = SimpleDateFormat().setTime(item.musicTime)

            binding.playButton.setOnClickListener {
                onStartClickListener(context as ItemOnClickListener)
                itemStartClickListener.onStartMusic(item)
            }
            binding.pauseButton.setOnClickListener {
                onPauseClickListener(context as ItemOnClickListener)
                itemPauseClickListener.onPauseMusic(item)
            }
            binding.stopButton.setOnClickListener {
                onStopClickListener(context as ItemOnClickListener)
                itemStopClickListener.onStopMusic(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticeViewHolder {
        return PracticeViewHolder(ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PracticeViewHolder, position: Int) {
        holder.bind(musicList[position])
    }

    override fun getItemCount(): Int = musicList.size
}

