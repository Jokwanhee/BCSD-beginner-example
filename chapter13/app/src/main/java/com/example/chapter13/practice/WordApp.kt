package com.example.chapter13.practice

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WordApp: Application() {
    val appContext: Context = this

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: WordApp
            private set
    }
}