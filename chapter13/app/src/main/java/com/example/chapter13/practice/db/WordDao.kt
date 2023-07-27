package com.example.chapter13.practice.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {
    @Query("SELECT * FROM word ORDER BY id DESC")
    fun getAll(): List<Word>

    @Query("SELECT * FROM wORD ORDER BY id DESC LIMIT 1")
    fun getLastedWord(): Word

    @Insert
    fun insert(word: Word)

    @Update
    fun update(word: Word)

    @Delete
    fun delete(word: Word)
}