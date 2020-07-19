package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

// 饼图半径
private val mRadius = 150f.dp2px()
private val angles = floatArrayOf(60f, 90f, 140f, 45f, 25f)
private val colors = intArrayOf(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN)
// 起始角度
private const val START_ANGLE = 0f
// 被选中的部分（脱离整体）
private const val SELECTED_INDEX = 0
private val TRANSLATION = 25f.dp2px()

class PieView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    // 饼图中心 x
    private var mCenterX: Int = 0
    // 饼图中心 y
    private var mCenterY: Int = 0

    private var mStartAngle = START_ANGLE

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    constructor(context: Context?) : this(context, null, 0)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        mCenterX = w / 2
        mCenterY = h / 2
    }

    override fun onDraw(canvas: Canvas) {
        for ((index, angle) in angles.withIndex()) {
            mPaint.color = colors[index]
            if (index == SELECTED_INDEX) {
                canvas.save()
                canvas.translate(TRANSLATION * cos(Math.toRadians((mStartAngle + angle / 2).toDouble())).toFloat(),
                    TRANSLATION * sin(Math.toRadians((mStartAngle + angle / 2).toDouble())).toFloat())
            }
            canvas.drawArc(mCenterX - mRadius, mCenterY - mRadius, mCenterX + mRadius, mCenterY + mRadius,
                mStartAngle, angle, true, mPaint)
            mStartAngle += angle
            if (index == SELECTED_INDEX) {
                canvas.restore()
            }
        }
        mStartAngle = START_ANGLE
    }
}