package com.example.chapter11.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun main() {
    GlobalScope.launch {
        delay(1000L)
        print("hihi")
    }
    println("hi")
    Thread.sleep(2000L)
}





