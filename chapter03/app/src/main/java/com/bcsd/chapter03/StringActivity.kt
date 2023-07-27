package com.bcsd.chapter03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import com.bcsd.chapter03.databinding.ActivityStringBinding

class StringActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStringBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStringBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}