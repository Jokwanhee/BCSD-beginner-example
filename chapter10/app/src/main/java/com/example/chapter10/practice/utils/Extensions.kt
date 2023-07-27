package com.example.chapter10.practice.utils

import java.text.SimpleDateFormat

fun SimpleDateFormat.setTime(duration:Long?) =
    SimpleDateFormat("mm:ss").format(duration)
