package com.example.chapter13.practice.repository

import android.net.Uri
import android.util.Log
import com.example.chapter13.practice.WordApp
import com.example.chapter13.practice.db.AppDatabase
import com.example.chapter13.practice.db.Word
import com.example.chapter13.practice.db.WordLocalDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordLocalDataSource: WordLocalDataSource
): WordRepository {

    override suspend fun getAll(): List<Word> {
        return wordLocalDataSource.getAll()
    }

    override suspend fun getLatestWord(): Word {
        return wordLocalDataSource.getLatestWord()
    }

    override suspend fun insert(word: Word) {
        wordLocalDataSource.insert(word)
    }

    override suspend fun update(word: Word) {
        wordLocalDataSource.update(word)
    }

    override suspend fun delete(word: Word) {
        wordLocalDataSource.delete(word)
    }
}