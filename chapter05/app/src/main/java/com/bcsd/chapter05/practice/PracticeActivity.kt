package com.bcsd.chapter05.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import com.bcsd.chapter05.R
import com.bcsd.chapter05.databinding.ActivityPracticeBinding

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeBinding
    private lateinit var practiceFragment: PracticeFragment

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewNumber.text = count.toString()

        onClickDialogButton()
        onClickCountButton()
        onClickRandomButton()
    }

    private fun onClickDialogButton(){
        with(binding.buttonAlertDialog){
            val builder = AlertDialog.Builder(this@PracticeActivity)
            builder.apply {
                setTitle(R.string.dialog_title)
                setMessage(R.string.dialog_content)
                setPositiveButton(R.string.dialog_positive){ dialog, id ->
                    Log.d("로그", "positive - onClick")
                    count += 1

                    binding.textViewNumber.text = count.toString()
                    // return true / false 차이점 알아보기
                }
                setNegativeButton(R.string.dialog_negative){dialog, id ->
                    Toast.makeText(this@PracticeActivity, "Negative Button Click", Toast.LENGTH_SHORT).show()

                    Log.d("로그", "negative - onClick")
                }
                setNeutralButton(R.string.dialog_neutral){dialog, id ->
                    Log.d("로그", "neutral - onClick")
                }
                setCancelable(false)
                create()
            }
            setOnClickListener {
                builder.show()
            }
        }
    }
    private fun onClickCountButton(){
        with(binding.buttonCount){
            setOnClickListener {
                count += 1
                binding.textViewNumber.text = count.toString()
            }
        }
    }
    private fun onClickRandomButton(){
        with(binding.buttonRandom){
            setOnClickListener {
//                practiceFragment = PracticeFragment.newInstance(count)
                val bundle = bundleOf()
                practiceFragment = PracticeFragment()
                bundle.putInt("count", count)
                practiceFragment.arguments = bundle

                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container_view_practice, practiceFragment, MyObj.TAG)
                    .commit()
            }

        }
    }
}