package com.example.chapter12

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.chapter12.databinding.ActivityAddBinding
import com.example.chapter12.db.AppDatabase
import com.example.chapter12.db.Word
import com.example.chapter12.repository.WordRepositoryImpl
import com.example.chapter12.utils.FLAG
import com.example.chapter12.utils.FlagResult
import com.example.chapter12.viewmodel.WordViewModel
import com.google.android.material.chip.Chip

class AddActivity : AppCompatActivity() {
    private val viewModel: WordViewModel by viewModels {
        WordViewModel.WordViewModelProvider(WordRepositoryImpl(application))
    }
    private var originWord: Word? = null
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
            it.lifecycleOwner = this
        }
        initView()
    }

    private fun initView() {
        // getParcelableExtra deprecated
        originWord = intent?.getParcelableExtra(FLAG.WORD)
//        originWord = intent?.getParcelableExtra(FLAG.WORD, Word::class.java)
        originWord?.let { word ->
            binding.textInputEditText.setText(word.text)
            binding.meanTextInputEditText.setText(word.mean)
        }
    }

    fun addWord() {
        when(intent.getStringExtra(FLAG.FLAG)) {
            FlagResult.ADD -> {
                viewModel.insert(
                    Word(
                        binding.textInputEditText.text.toString(),
                        binding.meanTextInputEditText.text.toString()
                    )
                )
                setResult(RESULT_OK, Intent().putExtra(FLAG.ADD_WORD, true))
            }
            FlagResult.UPDATE -> {
                val word = originWord?.copy(
                    text = binding.textInputEditText.text.toString(),
                    mean = binding.meanTextInputEditText.text.toString()
                )
                setResult(RESULT_OK, Intent().putExtra(FLAG.UPDATE_WORD, word))
            }
        }
        finish()
    }
}