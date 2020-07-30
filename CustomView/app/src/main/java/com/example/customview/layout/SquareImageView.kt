package com.example.customview.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

class SquareImageView(context: Context?, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    // TODO: 2020/7/30 如果在 xml 中把宽高都设置成 match_parent，方形就会失效
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val squareSize = min(width, height)
        Log.i("jiajiaojie", "$width, $height")
        setMeasuredDimension(squareSize, squareSize)
    }

}