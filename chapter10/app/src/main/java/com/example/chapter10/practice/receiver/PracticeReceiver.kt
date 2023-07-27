package com.example.chapter10.practice.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PracticeReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action){
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "배터리가 부족합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}