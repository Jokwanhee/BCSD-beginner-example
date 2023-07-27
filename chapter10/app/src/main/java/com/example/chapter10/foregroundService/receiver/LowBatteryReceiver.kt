package com.example.chapter10.foregroundService.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class LowBatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("로그", "onReceive() -> ${intent?.action}")
        when (intent?.action){
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "배터리가 부족합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}