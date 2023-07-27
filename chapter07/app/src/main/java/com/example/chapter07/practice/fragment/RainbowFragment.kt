package com.example.chapter07.practice.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.chapter07.R
import com.example.chapter07.databinding.FragmentABinding
import com.example.chapter07.databinding.FragmentRainbowBinding
import com.example.chapter07.practice.adapter.RainbowAdapter

class RainbowFragment: Fragment() {
    private lateinit var binding: FragmentRainbowBinding
    private lateinit var rainbowAdapter: RainbowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("로그", "RainbowFragment - onCreateView()")

        binding = FragmentRainbowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("로그", "RainbowFragment - onViewCreated()")

        val rainbowList = mutableListOf<ColorItem>()
        rainbowList.apply {
            add(ColorItem(R.color.red))
            add(ColorItem(R.color.orange))
            add(ColorItem(R.color.yellow))
            add(ColorItem(R.color.green))
            add(ColorItem(R.color.blue))
            add(ColorItem(R.color.blue9))
            add(ColorItem(R.color.purple))
        }

        rainbowAdapter = RainbowAdapter(rainbowList)
        binding.rainbowViewPager.adapter = rainbowAdapter

        val onBackPressedCallback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Log.d("로그", "Rainbow")
                if (binding.rainbowViewPager.currentItem == 0){
                } else {
                    binding.rainbowViewPager.currentItem = binding.rainbowViewPager.currentItem - 1
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    override fun onStart() {
        Log.d("로그", "RainbowFragment - onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.d("로그", "RainbowFragment - onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d("로그", "RainbowFragment - onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d("로그", "RainbowFragment - onStop()")
        super.onStop()
    }
}

data class ColorItem(
    val color: Int
)