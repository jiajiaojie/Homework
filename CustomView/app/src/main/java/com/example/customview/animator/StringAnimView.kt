package com.example.customview.animator

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.dp2px
import kotlin.math.abs

val stringArray = arrayOf(
    "北京市",
    "天津市",
    "上海市",
    "重庆市",
    "河北省",
    "山西省",
    "辽宁省",
    "吉林省",
    "黑龙江省",
    "江苏省",
    "浙江省",
    "安徽省",
    "福建省",
    "江西省",
    "山东省",
    "河南省",
    "湖北省",
    "湖南省",
    "广东省",
    "海南省",
    "四川省",
    "贵州省",
    "云南省",
    "陕西省",
    "甘肃省",
    "青海省",
    "台湾省",
    "内蒙古自治区",
    "广西壮族自治区",
    "西藏自治区",
    "宁夏回族自治区",
    "新疆维吾尔自治区",
    "香港特别行政区",
    "澳门特别行政区"
)

class StringAnimView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var displayString = "北京市"
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint().apply {
        isAntiAlias = true
        textSize = 50.dp2px()
        textAlign = Paint.Align.CENTER
        isFakeBoldText = true
    }

    override fun onDraw(canvas: Canvas) {
        val fontMetrics = paint.fontMetrics
        canvas.drawText(displayString, (width / 2).toFloat(),
            height / 2 - abs(fontMetrics.ascent + fontMetrics.descent) / 2, paint)
    }

    class StringEvaluator : TypeEvaluator<String> {
        override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
            val startIndex = stringArray.indexOf(startValue)
            val endIndex = stringArray.indexOf(endValue)
            return stringArray[(startIndex + fraction * (endIndex - startIndex)).toInt()]
        }
    }
}