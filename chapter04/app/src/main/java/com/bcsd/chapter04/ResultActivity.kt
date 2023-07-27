package com.bcsd.chapter04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import com.bcsd.chapter04.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)


        binding.textViewResult.text = "name : $name, age : $age"

        binding.buttonResult.setOnClickListener {
            val message = "result 에서 보낸 데이터 메시지"
            // Intent 객체 생성하기
            val myIntent = Intent()
            myIntent.putExtra("message", message)
            setResult(RESULT_OK, myIntent)
            finish()
        }
    }
}