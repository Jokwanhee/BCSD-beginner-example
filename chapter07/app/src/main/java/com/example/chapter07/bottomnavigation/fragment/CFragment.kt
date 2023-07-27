package com.example.chapter07.bottomnavigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chapter07.databinding.FragmentABinding
import com.example.chapter07.databinding.FragmentBBinding
import com.example.chapter07.databinding.FragmentCBinding

class CFragment: Fragment() {
    private lateinit var binding: FragmentCBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }
}