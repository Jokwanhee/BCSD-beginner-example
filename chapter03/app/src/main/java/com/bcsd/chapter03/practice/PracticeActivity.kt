package com.bcsd.chapter03.practice

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcsd.chapter03.R
import com.bcsd.chapter03.databinding.ActivityPracticeBinding

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
        getString(R.string.id)
        getString(R.string.test)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}