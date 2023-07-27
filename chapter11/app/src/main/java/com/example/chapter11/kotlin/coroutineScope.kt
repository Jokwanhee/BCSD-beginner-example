package com.example.chapter11.kotlin

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    doTest1()
//    doTest3()
//    doTest4()
//    doTest5()
    doTest6()
}

fun doTest1() = runBlocking {
    println("before job")
    var job = launch {
        delay(2000L)
        println("job launch")
    }
    println("after job")

    println("before job2")
    var job2 = runBlocking {
        delay(2000L)
        println("job2 runBlocking")
    }
    println("after job2")

    println("before cScope")
    var cScope = coroutineScope {
        delay(2000L)
        println("cScope")
    }
    println("after cScope")


}

fun doTest2() = runBlocking {
    launch {
        delay(1000L)
        println("launch1")
    }
    launch {
        delay(1000L)
        println("launch2")
    }
}
fun doTest3() = runBlocking {
    runBlocking {
        delay(1000L)
        println("runBlocking1")
    }
    runBlocking {
        delay(1000L)
        println("runBlocking2")
    }
}

fun doTest4() = runBlocking {
    coroutineScope {
        delay(1000L)
        println("coroutineScope1")
    }
    coroutineScope {
        delay(1000L)
        println("coroutineScope2")
    }
}

fun doTest5() = runBlocking {
//    launch {
//        delay(1000L)
//        println("launch1")
//    }
//    coroutineScope {
//        launch {
//            delay(1000L)
//            println("coroutineScope launch1")
//        }
//        println("coroutineScope1")
//    }
    runBlocking {
        launch {
            delay(1000L)
            println("runBlocking launch1")
        }
        println("runBlocking1")
    }
    launch {
        delay(1000L)
        println("launch2")
    }
}


fun doTest6() = runBlocking {
    cFunc()
}
suspend fun cFunc() = coroutineScope {
    runBlocking {
        delay(1000L)
        println("runBlocking1")
    }
    println("run1")
    launch {
        delay(1000L)
        println("coroutineScope1")
    }
    println("co1")
}