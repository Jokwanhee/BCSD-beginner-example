package com.example.chapter12.repository

import androidx.lifecycle.LiveData
import com.example.chapter12.db.Word
import kotlinx.coroutines.Deferred

interface WordRepository {
    suspend fun getAll(): List<Word>
    suspend fun getLatestWord(): Word
    suspend fun insert(word: Word?)
    suspend fun update(word: Word?)
    suspend fun delete(word: Word?)
}