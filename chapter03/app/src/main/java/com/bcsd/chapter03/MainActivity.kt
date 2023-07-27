package com.bcsd.chapter03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcsd.chapter03.databinding.ActivityMainBinding
import com.bcsd.chapter03.practice.PracticeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            buttonAnimation.setOnClickListener {
                startActivity(Intent(this@MainActivity, AnimationActivity::class.java))
            }
            buttonColor.setOnClickListener {
                startActivity(Intent(this@MainActivity, ColorActivity::class.java))
            }
            buttonDrawable.setOnClickListener {
                startActivity(Intent(this@MainActivity, DrawableActivity::class.java))
            }
            buttonMenu.setOnClickListener {
                startActivity(Intent(this@MainActivity, MenuActivity::class.java))
            }
            buttonString.setOnClickListener {
                startActivity(Intent(this@MainActivity, StringActivity::class.java))
            }
            buttonCustom.setOnClickListener {
                startActivity(Intent(this@MainActivity, CustomViewActivity::class.java))
            }
            buttonPractice.setOnClickListener {
                startActivity(Intent(this@MainActivity, PracticeActivity::class.java))
            }
        }


    }
}