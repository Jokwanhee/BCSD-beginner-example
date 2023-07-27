package com.bcsd.chapter02.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bcsd.chapter02.*
import com.bcsd.chapter02.fragment.ConstraintFragment
import com.bcsd.chapter02.fragment.FrameFragment
import com.bcsd.chapter02.fragment.LinearFragment
import com.bcsd.chapter02.fragment.RelativeFragment

class ViewPagerAdapter(
    activity: MainActivity
):FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return LinearFragment.newInstance()
            1 -> return RelativeFragment.newInstance()
            2 -> return FrameFragment.newInstance()
            else -> return ConstraintFragment.newInstance()
        }
    }
}