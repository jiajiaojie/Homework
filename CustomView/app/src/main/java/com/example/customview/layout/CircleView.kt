package com.example.customview.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp2px

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val padding = 50.dp2px()
    private val radius = 100.dp2px()

    private val size = (padding + radius) * 2

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 不需要 super.onMeasure
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize(size.toInt(), widthMeasureSpec)
        val height = resolveSize(size.toInt(), heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(padding + radius, padding + radius, radius, paint)
    }
}