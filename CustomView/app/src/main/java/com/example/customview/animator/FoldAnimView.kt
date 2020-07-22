package com.example.customview.animator

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.R
import com.example.customview.dp2px
import com.example.customview.getBitmap

class FoldAnimView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val imageSize = 200.dp2px()
    private val imageMargin = 100.dp2px()
    var topFoldAngle: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomFoldAngle: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    var foldLineAngle: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val camera = Camera().apply {
        setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }

    private val bitmap = getBitmap(resources, R.drawable.avatar_rengwuxian, imageSize.toInt())

    private val paint = Paint().apply {
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        // 上半部分
        canvas.save()
        canvas.translate(imageMargin + imageSize / 2, imageMargin + imageSize / 2)
        canvas.rotate(-foldLineAngle)
        camera.save()
        camera.rotateX(topFoldAngle)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-imageSize, -imageSize, imageSize, 0f)
        canvas.rotate(foldLineAngle)
        canvas.translate(-(imageMargin + imageSize / 2), -(imageMargin + imageSize / 2))
        canvas.drawBitmap(bitmap, imageMargin, imageMargin, paint)
        canvas.restore()

        // 下半部分
        canvas.save()
        canvas.translate(imageMargin + imageSize / 2, imageMargin + imageSize / 2)
        canvas.rotate(-foldLineAngle)
        camera.save()
        camera.rotateX(bottomFoldAngle)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-imageSize, 0f, imageSize, imageSize)
        canvas.rotate(foldLineAngle)
        canvas.translate(-(imageMargin + imageSize / 2), -(imageMargin + imageSize / 2))
        canvas.drawBitmap(bitmap, imageMargin, imageMargin, paint)
        canvas.restore()
    }
}