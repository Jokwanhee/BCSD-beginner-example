package com.example.chapter13.practice.base

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity() {
    abstract val layoutId: Int

    private lateinit var _binding: T
    val binding: T
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        initState()
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    abstract fun initView()
    abstract fun subscribe()
    abstract fun initEvent()
    open fun initState() {
        initView()
        initEvent()
        subscribe()
    }
}