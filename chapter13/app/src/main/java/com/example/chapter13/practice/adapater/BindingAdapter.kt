package com.example.chapter13.practice.adapater

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.chapter13.practice.db.Word

@BindingAdapter("text")
fun TextView.setText(word: Word?){
    if (word == null){
        return
    }
    text = word.text
}

@BindingAdapter("mean")
fun TextView.setMean(word: Word?){
    if (word == null){
        return
    }
    text = word.mean
}