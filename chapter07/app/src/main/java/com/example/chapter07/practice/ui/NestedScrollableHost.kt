package com.example.chapter07.practice.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue
import kotlin.math.sign

class NestedScrollableHost: FrameLayout {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)

    private var touchSlop = 0
    private var initialX = 0f
    private var initialY = 0f
    private val parentViewPager: ViewPager2?
        get() {
            var v: View? = parent as? View
            while (v != null && v !is ViewPager2){
                v = v.parent as? View
            }
            return v as? ViewPager2
        }

    private val child: View? get() = if (childCount > 0) getChildAt(0) else null

    init {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    private fun canChildScroll(orientation: Int, delta: Float): Boolean {
        val direction = -delta.sign.toInt()
        return when (orientation){
            0 -> child?.canScrollHorizontally(direction) ?: false
            1 -> child?.canScrollVertically(direction) ?: false
            else -> throw IllegalArgumentException()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        handleInterceptTouchEvent(ev)
        Log.d("로그", "onInterceptTouchEvent event $ev")
        Log.d("로그", "onInterceptTouchEvent event x ${ev.x}")
        Log.d("로그", "onInterceptTouchEvent event y ${ev.y}")
        Log.d("로그", "onInterceptTouchEvent event orientation ${ev.orientation}")
        Log.d("로그", "onInterceptTouchEvent event parentViewPager ${parentViewPager?.orientation}")
        Log.d("로그", "onInterceptTouchEvent enabled ${this.isEnabled}")

        return super.onInterceptTouchEvent(ev)
    }

    private fun handleInterceptTouchEvent(ev: MotionEvent) {
        val orientation = parentViewPager?.orientation ?: return

        if (!canChildScroll(orientation, -1f) && !canChildScroll(orientation, 1f)){
            return
        }

        if (ev.action == MotionEvent.ACTION_DOWN){
            initialX = ev.x
            initialY = ev.y
            parent.requestDisallowInterceptTouchEvent(true)
        } else if (ev.action == MotionEvent.ACTION_MOVE){
            val dx = ev.x.minus(initialX)
            val dy = ev.y.minus(initialY)
            val isVpHorizontal = orientation == ORIENTATION_HORIZONTAL

            // times = *
            val scaledDx = dx.absoluteValue.times(if (isVpHorizontal) .5f else 1f)
            val scaledDy = dy.absoluteValue.times(if (isVpHorizontal) 1f else .5f)

            if (scaledDx > touchSlop || scaledDy > touchSlop){
                if (isVpHorizontal == (scaledDx > scaledDy)){
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    if (canChildScroll(orientation, if (isVpHorizontal) dx else dy)){
                        parent.requestDisallowInterceptTouchEvent(true)
                    } else {
                        parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
            }
        }
    }
}