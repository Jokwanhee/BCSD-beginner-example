package com.example.chapter07.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.chapter07.R
import com.example.chapter07.databinding.ActivityMainBinding
import com.example.chapter07.practice.adapter.PracticeAdapter

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val practiceAdapter by lazy { PracticeAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.practiceViewpagerContainer.adapter = practiceAdapter
        binding.practiceViewpagerContainer.isUserInputEnabled = false

        binding.practiceBottomNavi.setOnItemSelectedListener {
            Log.d("로그", "itemId : ${it.itemId}")
            when(it.itemId){
                R.id.action_rainbow -> { binding.practiceViewpagerContainer.currentItem = 0 }
                R.id.action_number -> { binding.practiceViewpagerContainer.currentItem = 1 }
                R.id.action_alphabet -> { binding.practiceViewpagerContainer.currentItem = 2 }
            }
            true
        }

        binding.practiceViewpagerContainer.registerOnPageChangeCallback(object:
            OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("로그", "Activity - onPageSelected position : $position")
                binding.practiceBottomNavi.menu.getItem(position).isChecked = true
            }
        })

    }
}

