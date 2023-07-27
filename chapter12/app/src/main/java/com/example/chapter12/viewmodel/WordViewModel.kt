package com.example.chapter12.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chapter12.adapter.WordAdapter
import com.example.chapter12.db.Word
import com.example.chapter12.repository.WordRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class WordViewModel(
    private val repository: WordRepository
) : ViewModel() {
    private val _word = MutableLiveData<Word>()
    val word: LiveData<Word> by lazy { _word }

    private val _wordList = MutableLiveData<List<Word>>()
    val wordList: LiveData<List<Word>> by lazy { _wordList }

    fun getAll() {
        viewModelScope.launch {
            _wordList.value = repository.getAll()
        }
    }

    fun getLatestWord() {
        viewModelScope.launch {
            _word.value = repository.getLatestWord()
        }
    }

    fun setWord(word: Word) {
        _word.value = word
    }

    fun insert(word: Word?) {
        viewModelScope.launch {
            repository.insert(word)
        }
    }

    fun update(word: Word?, currentList: List<Word>) {
        val job = viewModelScope.launch {
            repository.update(word)
        }
        if (job.isCompleted){
            val items = _wordList.value as MutableList
            val idx = currentList.indexOfFirst { it.id == word?.id }
            items[idx] = word!!
            _wordList.value = items
        }
    }

    fun delete(word: Word?) {
        val job = viewModelScope.launch {
            repository.delete(word)
        }
        if (job.isCompleted){
            val items = _wordList.value as MutableList
            items.remove(word)
            _wordList.value = items
        }
    }

    class WordViewModelProvider(
        private val repository: WordRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WordViewModel(repository) as T
        }
    }
}