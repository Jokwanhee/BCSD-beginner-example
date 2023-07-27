package com.example.chapter07.tablayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chapter07.bottomnavigation.fragment.AFragment
import com.example.chapter07.bottomnavigation.fragment.BFragment

class TabLayoutAdapter(
    fa: FragmentActivity
): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = TAB_PAGE
    override fun createFragment(position: Int): Fragment = when(position){
        0,1,2,3,4 -> { AFragment() }
        else -> { BFragment() }
    }

    companion object {
        const val TAB_PAGE = 5
    }
}