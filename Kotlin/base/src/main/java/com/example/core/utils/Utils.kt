package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

val displayMetrics = Resources.getSystem().displayMetrics

// 扩展函数
fun Float.dp2px(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
}

//fun dp2px(dp: Float): Float {
//    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
//}

//fun toast(string: String?) {
//    toast(string, Toast.LENGTH_SHORT)
//}

// 用默认参数实现重载
fun toast(string: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.currentApplication, string, duration).show()
}