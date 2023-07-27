package com.example.chapter14.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val searchText = MutableLiveData<String>("")
}