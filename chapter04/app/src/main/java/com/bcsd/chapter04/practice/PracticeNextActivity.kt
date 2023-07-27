package com.bcsd.chapter04.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bcsd.chapter04.R

class PracticeNextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_next)
        // IllegalArgumentException
//        val numberText = findViewById<TextView>(R.id.button_count)
        val contentTextView = findViewById<TextView>(R.id.text_view_content)
        val numberTextView = findViewById<TextView>(R.id.text_view_next_number)


        val number = intent.getIntExtra(NUM.NUMBER, 0)
        val rangeRandom = (0..number).random()

//        val threadLocalRandom = ThreadLocalRandom.current().nextInt(1, number+1)
//        val secureRandom = SecureRandom().nextInt(number + 1)


        val content = "${getString(R.string.random_text)} $number"

        contentTextView.text = content
        numberTextView.text = rangeRandom.toString()
    }
}