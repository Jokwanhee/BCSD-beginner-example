package com.example.chapter11.practice

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter11.databinding.ActivityStopWatchBinding
import com.example.chapter11.practice.adapter.StopWatchAdapter
import com.example.chapter11.practice.model.LabTimeItems
import com.example.chapter11.practice.utils.START_STATE
import com.example.chapter11.practice.utils.STOP_STATE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat

class StopWatchActivityVersionCoroutine : AppCompatActivity() {
    private lateinit var binding: ActivityStopWatchBinding
    private lateinit var stopWatchAdapter: StopWatchAdapter
    private var labTimeList = mutableListOf<LabTimeItems>()
    private var labCount = 0
    private var currentTime = 0L
    private var runningTime = 0L
    private var labTime = 0L
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        rightButtonClickEventHandler()
        leftButtonClickEventHandler()

    }

    private fun initView() {
        binding.timeTextView.text = "00:00.00"
        stopWatchAdapter = StopWatchAdapter()
        val linearLayoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        binding.stopWatchListRecyclerViewContainer.apply {
            adapter = stopWatchAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun rightButtonClickEventHandler() {
        binding.startButton.setOnClickListener {
            isRunning = true
            val startTime = SystemClock.elapsedRealtime()
            val plusTime = currentTime
            changeStateButtonUI(START_STATE)
            Log.d("로그", "코루틴 외부 ${Thread.currentThread()}")
            CoroutineScope(Dispatchers.Main).launch {
                Log.d("로그", "코루틴 Main ${Thread.currentThread()}")
            }
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("로그", "코루틴 IO ${Thread.currentThread()}")
            }
            CoroutineScope(Dispatchers.Default).launch {
                Log.d("로그", "코루틴 Default ${Thread.currentThread()}")
            }
            CoroutineScope(Dispatchers.Unconfined).launch {
                Log.d("로그", "코루틴 Unconfined ${Thread.currentThread()}")
            }
            CoroutineScope(newSingleThreadContext("MyThread")).launch {
                Log.d("로그", "코루틴 newSingleThreadContext ${Thread.currentThread()}")
            }

            CoroutineScope(newSingleThreadContext("AA")).launch {
                Log.d("로그", "코루틴 내부 ${Thread.currentThread()}")

                while (isRunning) {
                    runningTime = SystemClock.elapsedRealtime() - startTime
                    labTime = plusTime + runningTime
                    binding.timeTextView.text =
                        SimpleDateFormat("mm:ss.SS").format(labTime)
                    delay(10L)
                }
            }
        }
        binding.stopButton.setOnClickListener {
            isRunning = false
            changeStateButtonUI(STOP_STATE)
            currentTime += runningTime
        }
    }

    private fun leftButtonClickEventHandler() {
        with(binding) {
            resetButton.setOnClickListener {
                timeTextView.text = "00:00.00"
                labCount = 0
                labTimeList = mutableListOf()
                currentTime = 0L
                runningTime = 0L
                labTime = 0L
                stopWatchAdapter.submitList(labTimeList)
            }
            labButton.setOnClickListener {
                labCount += 1
                val dataSet = LabTimeItems(
                    labCount,
                    SimpleDateFormat("mm:ss.SS").format(labTime)
                )
                labTimeList.add(dataSet)
                stopWatchAdapter.submitList(labTimeList.toMutableList())
            }
        }
    }

    private fun changeStateButtonUI(state: String) {
        with(binding) {
            when (state) {
                START_STATE -> {
                    startButton.isVisible = false
                    stopButton.isVisible = true
                    resetButton.isVisible = false
                    labButton.isVisible = true
                }

                STOP_STATE -> {
                    startButton.isVisible = true
                    stopButton.isVisible = false
                    resetButton.isVisible = true
                    labButton.isVisible = false
                }
            }
        }
    }
}