package com.bcsd.chapter03

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.bcsd.chapter03.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

class CustomView(
    context: Context?,
    attrs: AttributeSet?
) : View(context, attrs) {
    private var myShapeColor: Int? = null

    init {
        if(attrs != null && context != null){
            val typedArr = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
            myShapeColor = typedArr.getColor(R.styleable.CustomView_myShapeColor, Color.YELLOW)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = myShapeColor ?: Color.BLACK
        canvas?.drawCircle(110f, 110f, 100f, paint)
    }
}