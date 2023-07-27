package com.example.chapter10.foregroundService

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_PAUSE
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_PLAY
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_STOP
import com.example.chapter10.applyService.service.BasicService
import com.example.chapter10.databinding.ActivityLocalResourceMusicBinding
import com.example.chapter10.foregroundService.service.ForegroundService

class ForegroundServiceActivity:AppCompatActivity() {
    private lateinit var binding: ActivityLocalResourceMusicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalResourceMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { playMusic() }
        binding.pauseButton.setOnClickListener { pauseMusic() }
        binding.stopButton.setOnClickListener { stopMusic() }
    }

    private fun playMusic(){
        val intent = Intent(this, ForegroundService::class.java).apply {
            action = MEDIA_MUSIC_PLAY
        }
        startService(intent)
    }
    private fun pauseMusic(){
        val intent = Intent(this, ForegroundService::class.java).apply {
            action = MEDIA_MUSIC_PAUSE
        }
        startService(intent)
    }
    private fun stopMusic(){
        val intent = Intent(this, ForegroundService::class.java).apply {
            action = MEDIA_MUSIC_STOP
        }
        startService(intent)
    }

    /** 앱 종료 시 서비스 종료시키기 - Memory leak */
    override fun onDestroy() {
        stopService(Intent(this, ForegroundService::class.java))
        super.onDestroy()
    }
}