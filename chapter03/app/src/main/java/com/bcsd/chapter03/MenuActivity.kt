package com.bcsd.chapter03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcsd.chapter03.databinding.ActivityDrawableBinding
import com.bcsd.chapter03.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}