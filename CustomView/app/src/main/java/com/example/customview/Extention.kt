package com.example.customview

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue


fun Float.dp2px(): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    Resources.getSystem().displayMetrics)

fun Int.dp2px(): Float = this.toFloat().dp2px()

fun getBitmap(resources: Resources, drawableId: Int, width: Int): Bitmap {
    val option = BitmapFactory.Options()
    option.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, drawableId, option)
    option.inDensity = option.outWidth
    option.inTargetDensity = width
    option.inJustDecodeBounds = false
    return BitmapFactory.decodeResource(resources, drawableId, option)
}