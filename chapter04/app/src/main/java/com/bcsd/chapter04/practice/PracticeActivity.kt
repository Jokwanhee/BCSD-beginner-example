package com.bcsd.chapter04.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.bcsd.chapter04.R
import com.bcsd.chapter04.databinding.ActivityPracticeBinding
import java.math.BigDecimal
import java.math.BigInteger

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeBinding

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_practice)
        setContentView(binding.root)

        binding.textViewNumber.text = count.toString()

        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonCount = findViewById<Button>(R.id.button_count)
        val buttonRandom = binding.buttonRandom

        buttonToast.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_msg), Toast.LENGTH_SHORT).show()
        }

        buttonCount.setOnClickListener {
            count += 1
//            binding.textViewNumber.setText(count.toString())
            binding.textViewNumber.text = count.toString()
        }

        buttonRandom.setOnClickListener {
            val intent = Intent(this, PracticeNextActivity::class.java).apply {
                action = Intent.ACTION_SEND
                putExtra(NUM.NUMBER, count)
            }
            startActivity(intent)
        }

        var number: BigInteger
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("count")
        binding.textViewNumber.text = count.toString()
    }
}