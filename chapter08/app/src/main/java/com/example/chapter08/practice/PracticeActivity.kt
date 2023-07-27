package com.example.chapter08.practice

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import com.example.chapter08.MainActivity
import com.example.chapter08.R
import com.example.chapter08.databinding.ActivityPracticeBinding

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeBinding
    private lateinit var randomFragment: RandomFragment
    private lateinit var builder: NotificationCompat.Builder
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** data binding UI */
        binding.numberTextView.text = count.toString()

        /** click toast button */
        binding.buttonToast.setOnClickListener {
            Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show()
        }

        /** click count button */
        with(binding) {
            buttonCount.setOnClickListener {
                count += 1
                binding.numberTextView.text = count.toString()
            }
        }

        /** notification */
        createNotificationChannel()



        /** click random button */
        binding.buttonRandom.setOnClickListener {
            val pendingIntent = createPendingIntent()
            initBuilder(pendingIntent)
            notifyNotification()
        }
    }

    /** 알림 채널 생성 */
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /** pendingIntent 만들기 */
    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(this, RandomActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("count", count)
        }
//        return TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack(intent)
//            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    /** 알림 builder 생성 */
    private fun initBuilder(pendingIntent: PendingIntent) {
        builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_description))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setOngoing(true)
    }

    /** 알림 띄우기 */
    private fun notifyNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            with(NotificationManagerCompat.from(this)) {
                if (ActivityCompat.checkSelfPermission(
                        this@PracticeActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@PracticeActivity,
                        arrayOf(PERM),
                        NOTIFICATION_PERM_REQUEST_CODE
                    )
                    return
                }
                notify(NOTIFICATION_ID, builder.build())
            }
        }
        else {
            NotificationManagerCompat.from(this)
                .notify(NOTIFICATION_ID, builder.build())
        }
    }

    /** 권한 관련 콜백 */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            NOTIFICATION_PERM_REQUEST_CODE -> {
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