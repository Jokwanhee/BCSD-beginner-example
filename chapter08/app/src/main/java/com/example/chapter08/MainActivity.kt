package com.example.chapter08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    /** not use sealed class */
//    fun updateDataA(a: A){
//        /** a - data binding update */
//    }
//    fun updateDataB(b: B){
//        /** b - data binding update */
//    }
//    fun updateDataC(c: C){
//        /** c - data binding update */
//    }

    /** use sealed class */
    fun updateData(test: Test){
        when(test){
            is Test.A -> TODO()
            is Test.B -> TODO()
            is Test.C -> TODO()
        }
    }
}

/** not use sealed class */
//data class A(
//    val a: Int
//)
//
//data class B(
//    val b: Int
//)
//
//data class C(
//    val c: Int
//)

/** use sealed class */
sealed class Test{
    data class A(
        val a: Int
    ):Test()
    data class B(
        val b: Int
    ): Test()
    data class C(
        val c: Int
    ): Test()
}