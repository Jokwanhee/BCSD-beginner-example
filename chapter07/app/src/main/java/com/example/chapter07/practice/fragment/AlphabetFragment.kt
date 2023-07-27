package com.example.chapter07.practice.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.chapter07.databinding.FragmentABinding
import com.example.chapter07.databinding.FragmentAlphabetBinding
import com.example.chapter07.practice.adapter.AlphabetAdapter
import com.example.chapter07.practice.adapter.NumberAdapter

class AlphabetFragment: Fragment() {
    private lateinit var binding: FragmentAlphabetBinding
    private lateinit var alphabetAdapter: AlphabetAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("로그", "AlphabetFragment - onCreateView()")

        binding = FragmentAlphabetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("로그", "AlphabetFragment - onViewCreated()")

        val alphabetList = mutableListOf<AlphabetItem>()
        for (i in 97..122){
            alphabetList.add(AlphabetItem(i.toChar().toString()))
        }

        alphabetAdapter = AlphabetAdapter(alphabetList)
        binding.alphabetViewPager.adapter = alphabetAdapter

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Log.d("로그", "Alphabet")
            }
        })
    }

    override fun onStart() {
        Log.d("로그", "AlphabetFragment - onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.d("로그", "AlphabetFragment - onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d("로그", "AlphabetFragment - onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d("로그", "AlphabetFragment - onStop()")
        super.onStop()
    }
}

data class AlphabetItem(
    val alphabet: String
)
