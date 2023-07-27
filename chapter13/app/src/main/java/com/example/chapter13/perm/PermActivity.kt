package com.example.chapter13.perm

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.chapter13.databinding.ActivityMainBinding

class PermActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val loadImageResult = registerForActivityResult(ActivityResultContracts.GetContent()) {
        Log.d("로그", "loadImageResult it: $it")
    }

    val readExternalRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Log.d("로그", "readExternalRequest it: $it")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        checkPermission()
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }

            shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
            }

            else -> {
                readExternalRequest.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun loadImage() {
        loadImageResult.launch("image/*")
    }
}