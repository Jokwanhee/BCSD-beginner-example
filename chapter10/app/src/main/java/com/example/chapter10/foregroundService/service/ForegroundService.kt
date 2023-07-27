package com.example.chapter10.foregroundService.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import com.example.chapter10.R
import com.example.chapter10.applyService.constant.CHANNEL_DESCRIPTION
import com.example.chapter10.applyService.constant.CHANNEL_ID
import com.example.chapter10.applyService.constant.CHANNEL_NAME
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_PAUSE
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_PLAY
import com.example.chapter10.applyService.constant.MEDIA_MUSIC_STOP
import com.example.chapter10.foregroundService.ForegroundServiceActivity
import com.example.chapter10.foregroundService.receiver.LowBatteryReceiver

class ForegroundService:Service() {
    private var mediaPlayer:MediaPlayer? = null
    private val receiver = LowBatteryReceiver()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        initReceiver()

        val playIcon = Icon.createWithResource(baseContext, R.drawable.ic_baseline_play_arrow_24)
        val pauseIcon = Icon.createWithResource(baseContext, R.drawable.ic_baseline_pause_24)
        val stopIcon = Icon.createWithResource(baseContext, R.drawable.ic_baseline_stop_24)

        val containerPendingIntent = PendingIntent.getActivity(
            baseContext,
            0,
            Intent(baseContext, ForegroundServiceActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP // 재사용하여 액티비티 호출
                },
            PendingIntent.FLAG_IMMUTABLE // 변경되지 않는 플래그
        )

        val pausePendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, ForegroundService::class.java).apply { action = MEDIA_MUSIC_PAUSE },
            PendingIntent.FLAG_IMMUTABLE
        )
        val playPendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, ForegroundService::class.java).apply { action = MEDIA_MUSIC_PLAY },
            PendingIntent.FLAG_IMMUTABLE
        )
        val stopPendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, ForegroundService::class.java).apply { action = MEDIA_MUSIC_STOP },
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = Notification.Builder(baseContext, CHANNEL_ID)
            .setStyle(
                Notification.MediaStyle()
                    .setShowActionsInCompactView(0,1,2)
            )
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.maple)
            .addAction(
                Notification.Action.Builder(
                    pauseIcon,
                    "Pause",
                    pausePendingIntent
                ).build()
            )
            .addAction(
                Notification.Action.Builder(
                    playIcon,
                    "Play",
                    playPendingIntent
                ).build()
            )
            .addAction(
                Notification.Action.Builder(
                    stopIcon,
                    "Stop",
                    stopPendingIntent
                ).build()
            )
            .setContentIntent(containerPendingIntent)
            .setContentTitle("음악 플레이어")
            .setContentText("음악이 재생중입니다...")
            .build()

        startForeground(100, notification)
    }

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

    private fun initReceiver(){
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        registerReceiver(receiver, filter)
    }

    private fun createNotificationChannel(){
        val channel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = CHANNEL_DESCRIPTION
            }

        val notificationManager = baseContext.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
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

    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        stopSelf()
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}