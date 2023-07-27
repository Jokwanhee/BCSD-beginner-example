package com.example.chapter13.practice.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chapter13.R
import com.example.chapter13.databinding.ActivityMainBinding
import com.example.chapter13.databinding.ActivityWordBinding
import com.example.chapter13.practice.adapater.WordAdapter
import com.example.chapter13.practice.base.BaseActivity
import com.example.chapter13.practice.db.AppDatabase
import com.example.chapter13.practice.db.Word
import com.example.chapter13.practice.utils.WordObject
import com.example.chapter13.practice.viewmodel.WordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordActivity : BaseActivity<ActivityWordBinding>(), WordAdapter.ItemClickListener {
    override val layoutId: Int
        get() = R.layout.activity_word

    private val viewModel: WordViewModel by viewModels()
    private lateinit var adapter: WordAdapter
    private lateinit var selectWord: Word

    private val addActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val addWord =
                result.data?.getParcelableExtra<Word>(WordObject.ADD_WORD)
//            result.data?.getBooleanExtra(WordObject.ADD_WORD, false) ?: false
            val updateWord =
                result.data?.getParcelableExtra<Word>(WordObject.UPDATE_WORD)
            if (result.resultCode == RESULT_OK) {
                if (addWord != null) {
                    addWordUI()
                    Glide.with(this)
                        .load(addWord.image)
                        .into(binding.imageView)
                }
                if (updateWord != null) {
                    updateWordUI(updateWord)
                    Glide.with(this)
                        .load(updateWord.image)
                        .into(binding.imageView)
                }
            }
        }

    override fun initView() {
        binding.also {
            it.view = this@WordActivity
            it.lifecycleOwner = this@WordActivity
            it.viewModel = viewModel
        }
        initRecyclerView()
        observeLiveData()
    }

    private fun initRecyclerView() {
        viewModel.getAll()
        adapter = WordAdapter(this)
        binding.wordRecyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        }
    }

    private fun observeLiveData() {
        viewModel.word.observe(this) {
            selectWord = it
        }
        viewModel.wordList.observe(this) {
            adapter.submitList(it.toList())
        }
    }

    override fun subscribe() {}
    override fun initEvent() {}

    private fun addWordUI() {
        viewModel.getAll()
        viewModel.getLatestWord()
    }

    private fun updateWordUI(updateWord: Word) {
        viewModel.update(updateWord, adapter.currentList)
        viewModel.setWord(updateWord)
    }

    fun addWord(){
        addActivityResult.launch(Intent(this, AddActivity::class.java).also {
            it.putExtra(WordObject.FLAG, WordObject.ADD)
        })
    }

    fun updateWord(){
        addActivityResult.launch(Intent(this, AddActivity::class.java).also {
            it.putExtra(WordObject.WORD, viewModel.word.value)
            it.putExtra(WordObject.FLAG, WordObject.UPDATE)
        })
    }

    fun deleteWord(){
        viewModel.delete(selectWord)
        binding.textTextView.text = ""
        binding.meanTextView.text = ""
        binding.imageView.setImageResource(0)
    }

    override fun onClick(word: Word) {
        viewModel.setWord(word)
        Glide.with(this)
            .load(word.image)
            .into(binding.imageView)
    }
}