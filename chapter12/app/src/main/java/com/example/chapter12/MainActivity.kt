package com.example.chapter12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter12.adapter.WordAdapter
import com.example.chapter12.databinding.ActivityMainBinding
import com.example.chapter12.db.Word
import com.example.chapter12.repository.WordRepositoryImpl
import com.example.chapter12.utils.FlagResult
import com.example.chapter12.utils.FLAG
import com.example.chapter12.viewmodel.WordViewModel

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private val viewModel: WordViewModel by viewModels {
        WordViewModel.WordViewModelProvider(WordRepositoryImpl(application))
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WordAdapter
    private lateinit var selectWord: Word

    private val addWordResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val isAddWord =
                result.data?.getBooleanExtra(FLAG.ADD_WORD, false) ?: false
            if (result.resultCode == RESULT_OK && isAddWord) {
                addWordUI()
            }
        }
    private val updateWordResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val updateWord =
                result.data?.getParcelableExtra<Word>(FLAG.UPDATE_WORD)
            if (result.resultCode == RESULT_OK && updateWord != null) {
                updateWordUI(updateWord)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        initRecyclerView()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.word.observe(this) {
            selectWord = it
        }
        viewModel.wordList.observe(this) {
            adapter.submitList(it.toList())
        }
    }

    private fun initRecyclerView(){
        viewModel.getAll()
        adapter = WordAdapter(this)
        binding.wordRecyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        }
    }

    private fun addWordUI() {
        viewModel.getLatestWord()
        viewModel.getAll()
    }

    private fun updateWordUI(updateWord: Word) {
        viewModel.update(updateWord, adapter.currentList)
        viewModel.setWord(updateWord)
    }

    fun addWord(){
        addWordResult.launch(Intent(this, AddActivity::class.java).also {
            it.putExtra(FLAG.FLAG, FlagResult.ADD)
        })
    }

    fun updateWord(){
        updateWordResult.launch(Intent(this, AddActivity::class.java).also {
            it.putExtra(FLAG.WORD, viewModel.word.value)
            it.putExtra(FLAG.FLAG, FlagResult.UPDATE)
        })
    }

    fun deleteWord() {
        viewModel.delete(selectWord)
        binding.textTextView.text = ""
        binding.meanTextView.text = ""
    }

    override fun onClick(word: Word) {
        viewModel.setWord(word)
    }
}