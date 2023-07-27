package com.example.chapter13.practice.repository

import com.example.chapter13.practice.db.Word

interface WordRepository {
    suspend fun getAll(): List<Word>
    suspend fun getLatestWord(): Word
    suspend fun insert(word: Word)
    suspend fun update(word: Word)
    suspend fun delete(word: Word)
}