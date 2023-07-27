package com.bcsd.chapter04

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.bcsd.chapter04.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val button: Button = findViewById<Button>(R.id.button_test)
        val buttonClick: Button = findViewById(R.id.button_onclick)


        // ==================== button listener method ====================
        // 1. 익명 클래스 + 무명 객체 사용

        binding.buttonOnclickOne.setOnClickListener(object: OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(this@MainActivity, "익명 클래스 + 무명 객체", Toast.LENGTH_SHORT).show()
            }
        })
        // 제일 좋은건 람다 쓰자;
        binding.buttonOnclickOne.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "익명 클래스 + 무명 객체",
                Toast.LENGTH_SHORT
            ).show()
        }
        // ==================== ====================
        // 2. 참조 객체 사용
        val listener = View.OnClickListener{
            when(it){
                binding.buttonOnclickTwo -> {
                    Toast.makeText(this@MainActivity, "참조 객체 - two", Toast.LENGTH_SHORT).show()
                }
                binding.buttonOnclickTwoSub -> {
                    Toast.makeText(this@MainActivity, "참조 객체 - two sub", Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.buttonOnclickTwo.setOnClickListener(this) // View.OnClickListener 상속 시 가능
        binding.buttonOnclickTwo.setOnClickListener(listener)
        binding.buttonOnclickTwoSub.setOnClickListener(listener)
        // ==================== ====================
        // 3. 액티비티 클래스에서 Interface View.OnClickListener 상속
        // 밑에 onClick 오버라이딩 함수 있음.
        // ==================== ====================
        // 4. xml 레이아웃 리소스에서 Button 의 onClick 속성 사용하기
        // 밑에 onCustomButtonClick 있음.
        // ==================== ====================

        // ==================== button set onClick and onLongClick ====================
        with(buttonClick){
            setOnClickListener {
                setBackgroundColor(Color.BLUE)
            }
            setOnLongClickListener {
                setBackgroundColor(Color.BLACK)
                // true : 다른 이벤트 동시 실행 안됨.
                // false : 다른 이벤트 동시 실행 됨.
                true
            }
        }

        binding.buttonLongClick.setOnClickListener {
            binding.buttonLongClick.setBackgroundColor(Color.BLUE)
        }
        binding.buttonLongClick.setOnLongClickListener {
            binding.buttonLongClick.setBackgroundColor(Color.BLACK)
            false
        }

        // ==================== ====================

        // ==================== snack bar ====================
        binding.buttonSnackBar.setOnClickListener {
            val snackBar = Snackbar.make(
                this@MainActivity,
                findViewById(R.id.constraint_layout_main),
                "Long Clicked",
                Snackbar.LENGTH_SHORT
            )

            snackBar.setAction("토스트 메시지 호출", object: OnClickListener{
                override fun onClick(p0: View?) {
                    Toast.makeText(this@MainActivity, "Snack button clicked", Toast.LENGTH_SHORT).show()
                }
            })
            snackBar.show()
        }
        // ==================== ====================


        // ==================== EditText TextWatcher ====================
        binding.editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("EditText", "beforeTextChanged() : $p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("EditText", "onTextChanged() : $p0")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("EditText", "afterTextChanged() : $p0")
            }
        })
        binding.editText.addTextChangedListener { it -> }
        // ==================== ====================


        // ==================== Toast ====================
        binding.buttonToast.setOnClickListener {
            Toast.makeText(applicationContext, "applicationContext", Toast.LENGTH_SHORT).show()
//            Toast.makeText(this, "this", Toast.LENGTH_SHORT).show()
//            Toast.makeText(this, "Long", Toast.LENGTH_LONG).show()
        }
        // ==================== ====================



    }

    override fun onClick(view: View?) {
        when(view){
            binding.buttonOnclickThree -> {
                Toast.makeText(this@MainActivity, "상속으로 구현", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onCustomButtonClick(view: View?){
        when(view){
            binding.buttonOnclickFour -> {
                Toast.makeText(this@MainActivity, "xml 속성 구현", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



