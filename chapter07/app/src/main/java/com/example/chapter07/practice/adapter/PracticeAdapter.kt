package com.example.chapter07.practice.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chapter07.practice.fragment.AlphabetFragment
import com.example.chapter07.practice.fragment.NumberFragment
import com.example.chapter07.practice.fragment.RainbowFragment
import java.lang.Exception

class PracticeAdapter(frag: FragmentActivity): FragmentStateAdapter(frag) {
    override fun getItemCount(): Int = PAGES
    override fun createFragment(position: Int): Fragment
    {
        Log.d("로그", "PracticeAdapter createFragment()")
        return when(position){
            0 -> { RainbowFragment() }
            1 -> { NumberFragment() }
            2 -> { AlphabetFragment() }
            else -> throw Exception()
        }
    }

    companion object {
        const val PAGES = 3
    }

}

