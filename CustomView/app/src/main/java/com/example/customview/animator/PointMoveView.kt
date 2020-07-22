package com.example.customview.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp2px

class PointMoveView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var pointF = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }

    val paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 20.dp2px()
        strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPoint(pointF.x, pointF.y, paint)
    }
}