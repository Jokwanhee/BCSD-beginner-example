package com.example.chapter07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chapter07.databinding.ActivityMainBinding
// https://www.meghandev.io/post/android-bottomnavigationview-example-tutorial
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.practiceBottomNavi.setOnItemSelectedListener {
            Log.d("로그", "it  $it")
            false
        }
    }
}