package com.example.chapter07.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter07.R
import com.example.chapter07.databinding.ActivityTabLayoutBinding
import com.example.chapter07.tablayout.adapter.TabLayoutAdapter
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Tab setting with addTab() */
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TAB 1"))
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TAB 2"))
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TAB 3"))

        /** adapter */
        binding.tabLayoutViewPagerContainer.adapter = TabLayoutAdapter(this)

        /** Tab string list */
        val tabTitle = mutableListOf<String>("1","2","3","4","5")

        /** TabLayoutMediator */
        TabLayoutMediator(
            binding.tabLayout,
            binding.tabLayoutViewPagerContainer
        )
        { tab, position ->
            binding.tabLayoutViewPagerContainer.currentItem = tab.position
            tab.text = tabTitle[position]
        }.attach()

    }
}