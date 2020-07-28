package com.example.customview.edittext

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.customview.R
import com.example.customview.dp2px

private val TEXT_SIZE = 12.dp2px()
private val TEXT_MARGIN = 8.dp2px()
private val HORIZONTAL_OFFSET = 5.dp2px()
private val VERTICAL_OFFSET = 23.dp2px()
private val EXTRA_VERTICAL_OFFSET = 16.dp2px()

class LabelEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    var useLabel = false
        set(value) {
            field = value
            invalidate()
            val paddingTop = if (value) (defaultPaddingTop + TEXT_SIZE + TEXT_MARGIN).toInt() else defaultPaddingTop
            setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        }

    var showLabel = false
    var defaultPaddingTop = 0

    // 如果 ObjectAnimator 没有设置起始值，需要改成 public 的，不然取不到初始值，默认用 0
    private var fraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var animator = ObjectAnimator.ofFloat(this, "fraction", 0f, 1f)

    init {
        defaultPaddingTop = paddingTop
        R.attr.useLabel
        val styleAttrs = context.obtainStyledAttributes(attrs, R.styleable.LabelEditText)
        useLabel = styleAttrs.getBoolean(R.styleable.LabelEditText_useLabel, true)
        styleAttrs.recycle()
    }

    private val labelPaint = Paint().apply {
        isAntiAlias = true
        textSize = TEXT_SIZE
        color = Color.MAGENTA
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (!hint.isNullOrEmpty() && useLabel) {
            labelPaint.alpha = (fraction * 0xff).toInt()
            val offsetY = VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - fraction)
            canvas.drawText(
                hint.toString(),
                HORIZONTAL_OFFSET,
                offsetY,
                labelPaint
            )
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (showLabel && text.isNullOrEmpty()) {
            showLabel = false
            animator?.reverse()
        } else if (!showLabel && !text.isNullOrEmpty()) {
            showLabel = true
            animator?.start()
        }
    }
}