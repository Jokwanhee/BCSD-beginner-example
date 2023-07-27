package com.example.chapter07.bottomnavigation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chapter07.bottomnavigation.fragment.AFragment
import com.example.chapter07.bottomnavigation.fragment.BFragment
import com.example.chapter07.bottomnavigation.fragment.CFragment

class BottomNaviAdapter(
    frag:FragmentActivity
): FragmentStateAdapter(frag) {
    override fun getItemCount(): Int = PAGE
    override fun createFragment(position: Int): Fragment = when(position){
        0 -> AFragment()
        1 -> BFragment()
        else -> CFragment()
    }

    companion object {
        const val PAGE = 3
    }
}