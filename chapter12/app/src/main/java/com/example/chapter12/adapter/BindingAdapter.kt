package com.example.chapter12.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.chapter12.db.Word

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
