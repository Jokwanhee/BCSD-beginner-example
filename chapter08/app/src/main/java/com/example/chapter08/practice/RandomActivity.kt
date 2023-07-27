package com.example.chapter08.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chapter08.R
import com.example.chapter08.databinding.ActivityRandomBinding
import java.util.concurrent.ThreadLocalRandom

class RandomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val count = intent.getIntExtra("count", 0)
        Log.d("로그", "get count : $count")
        var randomCount = 0
        if (count != 0) {
            randomCount = ThreadLocalRandom.current().nextInt(1, count)
        }

        initUi(randomCount, count)
    }

    private fun initUi(randNum: Int, count:Int) {
        binding.randomContentTextView.text = "Here is a random number between 0 and $count"
        binding.numberTextView.text = randNum.toString()
    }
}