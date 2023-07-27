package com.bcsd.chapter03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcsd.chapter03.databinding.ActivityColorBinding
import com.bcsd.chapter03.databinding.ActivityMainBinding

class ColorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityColorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}