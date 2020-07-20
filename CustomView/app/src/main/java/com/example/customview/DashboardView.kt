package com.example.customview

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

// 仪表盘半径
private val mRadius = 150f.dp2px()
// 仪表盘划过的角度
private const val DASHBOARD_ANGLE = 240f
// 起始角度
private const val mStartAngle = (360 - DASHBOARD_ANGLE) / 2 + 90
// 刻度个数
private const val MARK_COUNT = 20
// 当前显示的刻度值
private const val MARK_VALUE = 15
// 每个刻度的角度
private const val mOneMarkAngle = DASHBOARD_ANGLE / MARK_COUNT
// 刻度小矩形的宽
private val mMarkWidth = 2f.dp2px()
// 刻度小矩形的高
private val mMarkHeight = 10f.dp2px()
// 指针的长度
private val mPointerLength = 120f.dp2px()

class DashboardView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    // 仪表盘中心 x
    private var mCenterX: Int = 0
    // 仪表盘中心 y
    private var mCenterY: Int = 0

    private val mPath = Path()
    private val mMarkPath = Path()
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 3f.dp2px()
    }

    private var mPathMeasure: PathMeasure = PathMeasure().apply {
        setPath(mPath, false)
    }

    private lateinit var mEffect: PathDashPathEffect

    constructor(context: Context?) : this(context, null, 0)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        mCenterX = w / 2
        mCenterY = h / 2
        mPath.addArc(mCenterX - mRadius, mCenterY - mRadius, mCenterX + mRadius, mCenterY + mRadius,
            mStartAngle, DASHBOARD_ANGLE)
        mPathMeasure.setPath(mPath, false)
        mMarkPath.addRect(0f, 0f, mMarkWidth, mMarkHeight, Path.Direction.CCW)
        mEffect = PathDashPathEffect(mMarkPath, (mPathMeasure.length - mMarkWidth) / MARK_COUNT, 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        // 画轮廓
        canvas.drawPath(mPath, mPaint)
        // 画刻度
        mPaint.pathEffect = mEffect
        canvas.drawPath(mPath, mPaint)
        mPaint.pathEffect = null
        // 画指针
        canvas.drawLine(mCenterX.toFloat(), mCenterY.toFloat(),
            mCenterX + mPointerLength * cos(Math.toRadians((mStartAngle + mOneMarkAngle * MARK_VALUE).toDouble())).toFloat(),
            mCenterY + mPointerLength * sin(Math.toRadians((mStartAngle + mOneMarkAngle * MARK_VALUE).toDouble())).toFloat(),
            mPaint)
    }
}