package com.example.customview

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class FoldPageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val imageSize = 200.dp2px()
    private val imageMargin = 100.dp2px()
    private val angle = 30f

    private val camera = Camera().apply {
        rotateX(30f)
        setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }

    private val bitmap = getBitmap(resources, R.drawable.avatar_rengwuxian, imageSize.toInt())

    private val paint = Paint().apply {
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        // 平铺部分
        canvas.save()
        canvas.translate(imageMargin + imageSize / 2, imageMargin + imageSize / 2)
        canvas.rotate(-angle)
        canvas.clipRect(-imageSize, -imageSize, imageSize, 0f)
        canvas.rotate(angle)
        canvas.translate(-(imageMargin + imageSize / 2), -(imageMargin + imageSize / 2))
        canvas.drawBitmap(bitmap, imageMargin, imageMargin, paint)
        canvas.restore()

        // 折起部分
        canvas.save()
        canvas.translate(imageMargin + imageSize / 2, imageMargin + imageSize / 2)
        canvas.rotate(-angle)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-imageSize, 0f, imageSize, imageSize)
        canvas.rotate(angle)
        canvas.translate(-(imageMargin + imageSize / 2), -(imageMargin + imageSize / 2))
        canvas.drawBitmap(bitmap, imageMargin, imageMargin, paint)
        canvas.restore()
    }
}