package com.example.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CircleBorderView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    private val mWidth = 200f.dp2px()
    private val mPadding = 20f.dp2px()
    private val mBorder = 10f.dp2px()

    private val rect = RectF(mPadding, mPadding, mPadding + mWidth, mPadding + mWidth)
    private val bitmapRect = RectF(mPadding + mBorder, mPadding + mBorder,
        mPadding + mWidth - mBorder, mPadding + mWidth - mBorder)

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private val mBitmap = getBitmap(R.drawable.avatar_rengwuxian, (mWidth - 2 * mBorder).toInt())

    constructor(context: Context?) : this(context, null, 0)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onDraw(canvas: Canvas) {
        canvas.drawOval(rect, mPaint)
        val count = canvas.saveLayer(bitmapRect, null)
        canvas.drawOval(bitmapRect, mPaint)
        mPaint.xfermode = xfermode
        canvas.drawBitmap(mBitmap, bitmapRect.left, bitmapRect.top, mPaint)
        mPaint.xfermode = null
        canvas.restoreToCount(count)
    }

    private fun getBitmap(drawableId: Int, width: Int): Bitmap {
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, drawableId, option)
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        option.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(resources, drawableId, option)
    }

}