package com.example.chapter10.applyService.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.chapter10.R
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_PAUSE
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_PLAY
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_STOP


class BasicService:Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder? { return null }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action){
            MEDIA_MUSIC_PLAY -> { play() }
            MEDIA_MUSIC_PAUSE -> { pause() }
            MEDIA_MUSIC_STOP -> {
                stop()
                stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun play(){
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(baseContext, R.raw.maple_bgm)
        }
        mediaPlayer?.start()
    }
    private fun pause(){
        mediaPlayer?.pause()
    }
    private fun stop(){
        /** 순서 중요 : stop() -> release() */
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    /** 서비스 종료 시 메모리 처리 및 서비스 종료 */
    override fun onDestroy() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
        stopSelf()
        super.onDestroy()
    }
}
