package com.example.chapter11.practice.model

data class LabTimeItems(
    val id: Int,
    val duration: String
)

fun String.setLabTimeFormat(min:Int, sec:Int, deciSec:Int): String =
    String.format("%02d:%02d.%02d", min,sec,deciSec)