package com.example.chapter06.ext

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment

fun DialogFragment.setWidthPercent(percentage: Int){
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0,0,widthPixels,heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}

//fun DialogFragment.setPullScreen(){
//    dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT)
//}