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
import com.example.chapter11.practice.model.setLabTimeFormat
import com.example.chapter11.practice.utils.START_STATE
import com.example.chapter11.practice.utils.STOP_STATE
import java.text.SimpleDateFormat

class StopWatchActivityVersionThread : AppCompatActivity() {
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
            Log.d("로그", "Thread 외부 ${Thread.currentThread()}")
            Thread {
                Log.d("로그", "Thread 내부 ${Thread.currentThread()}")
                while (isRunning) {
                    runningTime = SystemClock.elapsedRealtime() - startTime
                    labTime = plusTime + runningTime
                    runOnUiThread {
                        binding.timeTextView.text =
                            SimpleDateFormat("mm:ss.SS").format(plusTime + runningTime)
                    }
                    Thread.sleep(10L)
                }
            }.start()


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
                labTimeList.clear()
                currentTime = 0L
                runningTime = 0L
                labTime = 0L
                stopWatchAdapter.submitList(labTimeList.toMutableList())
            }
            labButton.setOnClickListener {
                labCount += 1
                val dataSet = LabTimeItems(
                    labCount,
                    SimpleDateFormat("mm:ss.SS").format(labTime)
                )
                Log.d("로그", "data : $dataSet")
                labTimeList.add(dataSet)
                Log.d("로그", "data : $labTimeList")
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


