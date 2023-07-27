package com.example.chapter14.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepoViewModel: ViewModel() {
    val username = MutableLiveData<String>("")

    fun setUsername(name:String) {
        username.value = name
    }
}