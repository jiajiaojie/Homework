package com.example.customview.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

class SquareImageView(context: Context?, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 需要 super.onMeasure
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = measuredHeight
        val squareSize = min(width, height)
        Log.i("jiajiaojie", "$width, $height, result: $squareSize")
        setMeasuredDimension(squareSize, squareSize)
    }

}