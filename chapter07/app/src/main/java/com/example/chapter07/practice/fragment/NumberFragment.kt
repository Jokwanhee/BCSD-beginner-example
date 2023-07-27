package com.example.chapter07.practice.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.chapter07.databinding.FragmentABinding
import com.example.chapter07.databinding.FragmentNumberBinding
import com.example.chapter07.practice.adapter.NumberAdapter

class NumberFragment: Fragment() {
    private lateinit var binding: FragmentNumberBinding
    private lateinit var numberAdapter: NumberAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("로그", "NumberFragment - onCreateView()")
        binding = FragmentNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("로그", "NumberFragment - onViewCreated()")
        val numberList = mutableListOf<NumberItem>()
        for (i in 1..10){
            numberList.add(NumberItem(i.toString()))
        }
        Log.d("로그", "Numberlist ${numberList}")

        numberAdapter = NumberAdapter(numberList)
        binding.numberViewPager.adapter = numberAdapter

        binding.numberViewPager.registerOnPageChangeCallback(object: OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
            }
        })
    }

    override fun onStart() {
        Log.d("로그", "NumberFragment - onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.d("로그", "NumberFragment - onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d("로그", "NumberFragment - onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d("로그", "NumberFragment - onStop()")
        super.onStop()
    }
}

data class NumberItem(
    val number: String
)