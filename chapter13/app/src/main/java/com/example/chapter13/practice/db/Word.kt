package com.example.chapter13.practice.db

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "word")
data class Word(
    val text: String,
    val mean: String,
    val image: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
): Parcelable