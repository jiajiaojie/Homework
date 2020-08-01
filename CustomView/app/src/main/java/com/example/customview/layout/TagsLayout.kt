package com.example.customview.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagsLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val childRectList = mutableListOf<Rect>()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (childRectList.isEmpty()) {
            for (i in 1..childCount) {
                childRectList.add(Rect())
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var maxHeightOneLine = 0
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        for ((index, child) in children.withIndex()) {
            // widthUsed 参数传 0，是否超出宽度限制，通过之后代码手动判断
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (widthUsed + child.measuredWidth > specWidth) {
                widthUsed = 0
                heightUsed += maxHeightOneLine
                maxHeightOneLine = 0
            }
            // 如果子 View 的高度不一致，换行的时候以最高的为标准
            maxHeightOneLine = max(maxHeightOneLine, child.measuredHeight)
            childRectList[index].set(widthUsed, heightUsed,
                widthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            widthUsed += child.measuredWidth
        }
        Log.i("jiajiaojie", "child size: $widthUsed * $heightUsed")
        setMeasuredDimension(widthUsed, heightUsed)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val rect = childRectList[index]
            child.layout(rect.left, rect.top, rect.right, rect.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}