package com.example.chapter07.viewpager2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chapter07.viewpager2.fragment.SlideFragment
import com.example.chapter07.viewpager2.fragment.SubSlideFragment


/**
 * Use FragmentStateAdapter(FragmentActivity)
 * viewPager Page : NUM_PAGES
 */
class ScreenSlidePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun getItemCount(): Int = NUM_PAGES
    override fun createFragment(position: Int): Fragment = when(position){
        0 -> {SlideFragment()}
        1 -> {SubSlideFragment()}
        else -> SlideFragment()
    }

    companion object {
        private const val NUM_PAGES = 5
    }
}

