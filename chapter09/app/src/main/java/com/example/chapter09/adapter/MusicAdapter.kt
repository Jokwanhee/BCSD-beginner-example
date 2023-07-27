package com.example.chapter09.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chapter09.R
import com.example.chapter09.databinding.ItemMusicBinding
import com.example.chapter09.model.Music
import com.example.chapter09.utils.getAlbumUri
import com.example.chapter09.utils.setSimpleDateFormat

class MusicAdapter(
    private val musicList: List<Music>,
    val context: Context
): RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

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
        fun onStartMusic(music:Music)
        fun onPauseMusic(music:Music)
        fun onStopMusic(music:Music)
    }


    inner class MusicViewHolder(private val binding:ItemMusicBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(music:Music){
            binding.playButton.setOnClickListener {
                onStartClickListener(context as ItemOnClickListener)
                Log.d("로그", "music : $music")
                itemStartClickListener.onStartMusic(music)
            }
            binding.pauseButton.setOnClickListener {
                onPauseClickListener(context as ItemOnClickListener)
                itemPauseClickListener.onPauseMusic(music)
            }
            binding.stopButton.setOnClickListener {
                onStopClickListener(context as ItemOnClickListener)
                itemStopClickListener.onStopMusic(music)
            }

            binding.textViewTitle.text = music.title
//            binding.musicAlbumImage.setImageURI(getAlbumUri(music.albumId))
            Glide.with(context)
//                .load(getAlbumUri(music.albumId))
                .load(R.drawable.ic_launcher_foreground)
                .into(binding.musicAlbumImage)
            binding.textViewDuration.text = setSimpleDateFormat(music.duration)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(musicList[position])
    }

    override fun getItemCount(): Int = musicList.size
}