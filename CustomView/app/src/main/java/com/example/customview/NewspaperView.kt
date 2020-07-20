package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

private const val NEWS_CONTENT = "Pellentesque ullamcorper ultrices porta. Duis sed tincidunt augue. Ut lacus mi, sodales nec gravida id, rutrum eu nisl. Maecenas porta, neque sit amet accumsan aliquam, quam massa euismod purus, et sagittis dolor dui eget orci. Nulla facilisi. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Donec volutpat, nisi sed porttitor placerat, mi urna tempus enim, id ullamcorper magna quam nec justo. Curabitur quis ligula sodales, convallis elit quis, porttitor tellus. In viverra condimentum purus, aliquet semper nibh convallis in. Nulla elementum lacus et ipsum faucibus hendrerit at eu dui. Nullam ornare est ac velit faucibus vestibulum. Maecenas fringilla a nisl sed facilisis. In auctor risus velit, vitae porta dolor porttitor quis. Aliquam pulvinar imperdiet augue, vel sagittis tortor imperdiet at. Aliquam accumsan vel mi id cursus."

class NewspaperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val imageSize = 150.dp2px()
    private val imageMarginTop = 80.dp2px()

    private val bitmap = getBitmap(resources, R.drawable.avatar_rengwuxian, imageSize.toInt())

    private val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val textPaint = Paint().apply {
        isAntiAlias = true
        textSize = 16.dp2px()
    }

    override fun onDraw(canvas: Canvas) {
        // 画图片
        canvas.drawBitmap(bitmap, width - imageSize, imageMarginTop, bitmapPaint)
        // 写文字
        val fontMetrics = textPaint.fontMetrics
        var start = 0
        var offsetY = -fontMetrics.top
        val measuredWidth = floatArrayOf(0f)
        var lineWidth: Float
        while (start < NEWS_CONTENT.length) {
            lineWidth = if (offsetY < imageMarginTop || offsetY - textPaint.fontSpacing > imageMarginTop + imageSize) {
                width.toFloat()
            } else {
                width - imageSize
            }
            val count = textPaint.breakText(
                NEWS_CONTENT,
                start,
                NEWS_CONTENT.length,
                true,
                lineWidth,
                measuredWidth
            )
            canvas.drawText(NEWS_CONTENT, start, start + count, 0f, offsetY, textPaint)
            start += count
            offsetY += textPaint.fontSpacing
        }
    }

}