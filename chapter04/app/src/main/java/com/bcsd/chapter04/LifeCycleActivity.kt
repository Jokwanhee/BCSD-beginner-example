package com.bcsd.chapter04

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.bcsd.chapter04.databinding.ActivityLifeCycleBinding

class LifeCycleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLifeCycleBinding
    val getActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        // handle
        if (it.resultCode == RESULT_OK){
            val intent = it.data
            val editData = intent?.getStringExtra("message")
            binding.textViewLifeCycle.text = editData
        }
    }
    private lateinit var observer: MyLifeCycleObserver



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifeCycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("LifeCycleTest", "LifeCycleActivity - onCreate()")

        observer = MyLifeCycleObserver(this, this.activityResultRegistry)
        lifecycle.addObserver(observer)

        binding.buttonResultApi.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("name", "조관희")
                putExtra("age", 25)
            }
            intent.putExtra("a", 1)
            getActivity.launch(intent)
//            observer.launchActivity()
        }

        binding.buttonNaver.setOnClickListener {
            val webIntent: Intent = Uri.parse("https://www.naver.com").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }

    }

    // deprecated
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }



    override fun onStart() {
        super.onStart()
        Log.d("LifeCycleTest", "LifeCycleActivity - onStart()")

    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycleTest", "LifeCycleActivity - onResume()")

    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycleTest", "LifeCycleActivity - onPause()")

    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycleTest", "LifeCycleActivity - onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycleTest", "LifeCycleActivity - onDestroy()")

    }
}

class MyLifeCycleObserver(
    private val context: Context,
    private val registry : ActivityResultRegistry
): DefaultLifecycleObserver {
    lateinit var getActivity : ActivityResultLauncher<Intent>

    override fun onCreate(owner: LifecycleOwner) {
        getActivity = registry.register("key", owner, ActivityResultContracts.StartActivityForResult()){

        }
    }

    fun launchActivity() {
        getActivity.launch(Intent(context, ResultActivity::class.java))
    }
}