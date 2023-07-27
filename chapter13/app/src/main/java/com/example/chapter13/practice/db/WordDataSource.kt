package com.example.chapter13.practice.db

interface WordDataSource {
    fun getAll(): List<Word>
    fun getLatestWord(): Word
    fun insert(word: Word)
    fun update(word: Word)
    fun delete(word: Word)
}