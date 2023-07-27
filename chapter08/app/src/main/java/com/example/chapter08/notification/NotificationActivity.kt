package com.example.chapter08.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.chapter08.MainActivity
import com.example.chapter08.R
import com.example.chapter08.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var builder: NotificationCompat.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        /** PendingIntent */
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        /** builder */
        /** Android 7.1 : priority , Android 8.0 : importance */
        /** Need Channel : Android 8.0   */
       builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("알림 타이틀")
            .setContentText("알림 내용")
            .setStyle(NotificationCompat.BigTextStyle().bigText("큰 텍스트 스타일 적용"))
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            /** when you tab on Notification */
            .setContentIntent(pendingIntent)
            /** tab Notification -> notification remove */
            .setAutoCancel(true)

        notifyNotification()
    }

    /** 알림 생성 (버전 별 체크 필요) */
    private fun notifyNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            with(NotificationManagerCompat.from(this)){
                if (ActivityCompat.checkSelfPermission(
                        this@NotificationActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(this@NotificationActivity, arrayOf(PERM), NOTIFICATION_PERM_REQUEST_CODE)
                    return
                }
                notify(NOTIFICATION_ID, builder.build())
            }
        } else {
            NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel(){
        /** API 26 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID, // 채널 아이디
                CHANNEL_NAME, // 채널 이름
                NotificationManager.IMPORTANCE_DEFAULT // 채널 중요도
            ).apply {
                description = CHANNEL_DESCRIPTION
            }

            /** register channel with system */
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            NOTIFICATION_PERM_REQUEST_CODE -> {
                /** 알림 권한 허용 시 알림 바로 띄우기 */
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    notifyNotification()
                }
            }
        }
    }

    companion object {
        const val CHANNEL_ID = "채널 아이디"
        const val CHANNEL_NAME = "채널 이름"
        const val CHANNEL_DESCRIPTION = "채널 설명"
        const val NOTIFICATION_ID = 1
        const val PERM = Manifest.permission.POST_NOTIFICATIONS
        const val NOTIFICATION_PERM_REQUEST_CODE = 100
    }
}