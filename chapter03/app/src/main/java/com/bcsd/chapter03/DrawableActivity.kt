package com.bcsd.chapter03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcsd.chapter03.databinding.ActivityColorBinding
import com.bcsd.chapter03.databinding.ActivityDrawableBinding
import com.bcsd.chapter03.databinding.ActivityMainBinding

class DrawableActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrawableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawableBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}