package com.example.chapter11.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import androidx.core.view.ViewPropertyAnimatorListenerAdapter
import androidx.core.view.isVisible
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter11.databinding.ActivityStopWatchBinding
import com.example.chapter11.practice.adapter.StopWatchAdapter
import com.example.chapter11.practice.model.LabTimeItems
import com.example.chapter11.practice.model.setLabTimeFormat
import com.example.chapter11.practice.utils.START_STATE
import com.example.chapter11.practice.utils.STOP_STATE
import java.util.Timer
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.timer

class StopWatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStopWatchBinding
    private var currentDeciSecond = 0
    private var myTimer: Timer? = null
    private lateinit var stopWatchAdapter: StopWatchAdapter
    private var labTimeList = mutableListOf<LabTimeItems>()
    private var labCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        rightButtonClickEventHandler()
        leftButtonClickEventHandler()
    }

    private fun initView(){
        binding.timeTextView.text = "00:00.00"
        stopWatchAdapter = StopWatchAdapter()
        binding.stopWatchListRecyclerViewContainer.apply {
            adapter = stopWatchAdapter
            layoutManager =
                LinearLayoutManager(this@StopWatchActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun rightButtonClickEventHandler(){
        with(binding){
            startButton.setOnClickListener {
                val myHandler = MyHandler()
                changeStateButtonUI(START_STATE)

                // fixedRateTimer : 오차 문제 해결 타이머
                myTimer = timer(initialDelay = 0, period = 10){
                    currentDeciSecond += 1

                    var minutes = currentDeciSecond.div(100) / 60
                    var second = currentDeciSecond.div(100) % 60
                    var deciSecond = currentDeciSecond % 100

                    /** 1. runOnUiThread(Runnable) */
                    runOnUiThread {
                        timeTextView.text = String().setLabTimeFormat(minutes, second, deciSecond)
                    }
                    /** 2. View.post(Runnable) */
//                    root.post {
//                        timeTextView.text = String.format("%02d:%02d.%02d", minutes, second, deciSecond)
//                    }
                    /** 3. View.postDelayed(Runnable, Long) */
//                    root.postDelayed(Runnable{
//                        run {
//                            timeTextView.text = String().setLabTimeFormat(minutes, second, deciSecond)
//                        }
//                    }, 1000L)
                    /** 4. Handler */
//                    myHandler.post(Runnable {
//                        run {
//                            timeTextView.text = String().setLabTimeFormat(minutes, second, deciSecond)
//                        }
//                    })
                }
            }
            stopButton.setOnClickListener {
                changeStateButtonUI(STOP_STATE)
                myTimer?.cancel()
            }
        }
    }

    private fun leftButtonClickEventHandler(){
        with(binding){
            resetButton.setOnClickListener {
                timeTextView.text = "00:00.00"
                currentDeciSecond = 0
                labCount = 0
                labTimeList = mutableListOf()
                stopWatchAdapter.submitList(labTimeList)
            }
            labButton.setOnClickListener {
                labCount += 1
                var duration =
                    String().setLabTimeFormat(
                        currentDeciSecond.div(100) / 60,
                        currentDeciSecond.div(100) % 60,
                        currentDeciSecond % 100
                    )
                val dataSet = LabTimeItems(labCount, duration)
                labTimeList.add(dataSet)
                stopWatchAdapter.submitList(labTimeList.toMutableList())
            }
        }
    }


    private fun changeStateButtonUI(state:String) {
        with(binding){
            when (state){
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

class MyHandler():Handler(){
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
    }
}