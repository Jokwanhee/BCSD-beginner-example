package com.example.chapter10.localResourceMusic

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter10.R
import com.example.chapter10.databinding.ActivityLocalResourceMusicBinding

/** 로컬 리소스를 사용한 음원 플레이어 만들기 */

class LocalResourceMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocalResourceMusicBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalResourceMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { playMusic() }
        binding.pauseButton.setOnClickListener { pauseMusic() }
        binding.stopButton.setOnClickListener { stopMusic() }
    }

    private fun playMusic(){
        if (mediaPlayer == null){
            /** valid file name
             * 1. lowercase
             * 2. a-z
             * 3. 0-9
             * 4. underscore (_)
             * */
            mediaPlayer = MediaPlayer.create(this, R.raw.maple_bgm)
        }
        mediaPlayer?.start()
    }

    private fun pauseMusic(){
        mediaPlayer?.pause()
    }

    private fun stopMusic(){
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}