package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

private const val TEXT_CONTENT = "172"

class SportsView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val radius = 120f.dp2px()
    private val ringWidth = 20f.dp2px()

    private var centerX = 0f
    private var centerY = 0f

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = ringWidth
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        textSize = 60.dp2px()
        isFakeBoldText = true
        textAlign = Paint.Align.CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
    }

    override fun onDraw(canvas: Canvas) {
        // 画完整的背景圆环
        paint.color = Color.GRAY
        canvas.drawCircle(centerX, centerY, radius, paint)
        // 画进度（弧）
        paint.color = Color.MAGENTA
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(centerX - radius, centerY - radius, centerX + radius,centerY + radius,
            -90f, 240f, false, paint)
        // 画辅助线
//        paint.strokeWidth = 1f
//        paint.color = Color.BLACK
//        canvas.drawLine(0f, centerY, width.toFloat(), centerY, paint)
//        canvas.drawLine(centerX, 0f, centerX, height.toFloat(), paint)
        // 画中心文字
        val fontMetrics = textPaint.fontMetrics
        canvas.drawText(TEXT_CONTENT, centerX, centerY + Math.abs((fontMetrics.descent + fontMetrics.ascent)) / 2, textPaint)
    }
}