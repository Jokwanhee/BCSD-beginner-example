package com.example.chapter10.practice

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.example.chapter10.practice.service.PracticeService
import com.example.chapter10.practice.utils.Constants
import java.text.SimpleDateFormat
import java.util.jar.Manifest

abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity() {
    abstract val layoutId: Int
    private lateinit var _binding: T

    val binding:T
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        setContentView(binding.root)
        initState()
    }

    abstract fun initView()
    abstract fun initEvent()
    open fun initState() {
        initView()
        initEvent()
    }

    fun createServiceIntent(musicAction:String, id:String?): Intent =
        Intent(this, PracticeService::class.java).apply {
            action = musicAction
            if (id != null){
                putExtra("id", id)
            }
        }

    fun isPermissionGranted(perm:String):Boolean {
        return ActivityCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED
    }

}