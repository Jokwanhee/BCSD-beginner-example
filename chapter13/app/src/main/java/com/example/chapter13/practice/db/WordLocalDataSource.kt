package com.example.chapter13.practice.db

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WordLocalDataSource @Inject constructor(
    @ApplicationContext context: Context,
    private val wordDao: WordDao
): WordDataSource {
    override fun getAll(): List<Word> {
        return wordDao.getAll()
    }

    override fun getLatestWord(): Word {
        return wordDao.getLastedWord()
    }

    override fun insert(word: Word) {
        wordDao.insert(word)
    }

    override fun update(word: Word) {
        wordDao.update(word)
    }

    override fun delete(word: Word) {
        wordDao.delete(word)
    }
}