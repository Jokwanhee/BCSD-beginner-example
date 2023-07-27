package com.example.chapter10.practice.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import com.example.chapter10.R
import com.example.chapter10.foregroundService.receiver.LowBatteryReceiver
import com.example.chapter10.practice.PracticeActivity
import com.example.chapter10.practice.utils.Constants

class PracticeService: Service() {
    private var mediaPlayer: MediaPlayer? = null
    private val receiver = LowBatteryReceiver()
    var id: String? = null

    override fun onBind(p0: Intent?): IBinder? = null

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
            Intent(baseContext, PracticeActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP // 재사용하여 액티비티 호출
                },
            PendingIntent.FLAG_IMMUTABLE // 변경되지 않는 플래그
        )

        val pausePendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, PracticeService::class.java).apply { action = Constants.MEDIA_PAUSE }
                .putExtra("id", id),
            PendingIntent.FLAG_IMMUTABLE
        )
        val playPendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, PracticeService::class.java).apply {
                action = Constants.MEDIA_PLAY},
            PendingIntent.FLAG_IMMUTABLE
        )
        val stopPendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, PracticeService::class.java).apply { action = Constants.MEDIA_STOP },
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = Notification.Builder(baseContext, Constants.CHANNEL_ID_IN_MUSIC)
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

        startForeground(102, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action){
            Constants.MEDIA_PLAY -> {
                if (mediaPlayer == null){
                    id = intent.getStringExtra("id")
                    if (id != null){
                        mediaPlayer = MediaPlayer.create(
                            baseContext,
                            Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
                        )
                    }

                }
                mediaPlayer?.start()
            }
            Constants.MEDIA_PAUSE -> { mediaPlayer?.pause() }
            Constants.MEDIA_STOP -> {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotificationChannel(){
        val channel =
            NotificationChannel(
                Constants.CHANNEL_ID_IN_MUSIC,
                Constants.CHANNEL_NAME_IN_MUSIC,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = Constants.CHANNEL_DESC_IN_MUSIC
            }

        val notificationManager = baseContext.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    private fun initReceiver(){
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        stopSelf()
    }
}