package com.bcsd.chapter03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.bcsd.chapter03.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val animZoom = AnimationUtils.loadAnimation(this, R.anim.scale)
        val animDisappear = AnimationUtils.loadAnimation(this, R.anim.scale_alpha)
        val animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate)
        val animWave = AnimationUtils.loadAnimation(this, R.anim.wave)

        binding.buttonZoomInToOut.setOnClickListener {
            binding.imageViewAndroid.startAnimation(animZoom)
        }
        binding.buttonDisappear.setOnClickListener {
            binding.imageViewAndroid.startAnimation(animDisappear)
        }
        binding.buttonTranslate.setOnClickListener {
            binding.imageViewAndroid.startAnimation(animTranslate)
        }
        binding.buttonWave.setOnClickListener {
            binding.imageViewAndroid.startAnimation(animWave)
        }
    }
}