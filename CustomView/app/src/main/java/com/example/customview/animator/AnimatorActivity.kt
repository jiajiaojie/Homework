package com.example.customview.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.customview.R
import com.example.customview.dp2px

class AnimatorActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)
        view = findViewById<View>(R.id.view)
        view.setOnClickListener(this)
//        onClick(view)
    }

    override fun onClick(v: View) {
        // 圆缩放
        val animator = ObjectAnimator.ofFloat(view, "radius", 150.dp2px())
        animator.startDelay = 1000
        animator.start()

        // 点的移动
//        val animator = ObjectAnimator.ofObject(view, "pointF", PointMoveEvaluator(), PointF(300f.dp2px(), 200f.dp2px()))
//        animator.startDelay = 1000
//        animator.duration = 1000
//        animator.start()

        // 折叠动画
//        val topFold = ObjectAnimator.ofFloat(view, "topFoldAngle", -60f)
//        val foldLine = ObjectAnimator.ofFloat(view, "foldLineAngle", 270f)
//        val bottomFold = ObjectAnimator.ofFloat(view, "bottomFoldAngle", 60f)
//        val animator = AnimatorSet()
//        animator.startDelay = 1000
//        animator.duration = 1000
//        animator.playSequentially(topFold, foldLine, bottomFold)
//        animator.start()

        // 字符串动画
//        val animator = ObjectAnimator.ofObject(view, "displayString", StringAnimView.StringEvaluator(), "澳门特别行政区")
//        animator.startDelay = 1000
//        animator.duration = 3000
//        animator.interpolator = AccelerateDecelerateInterpolator()
//        animator.start()
    }

    class PointMoveEvaluator : TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val startX = startValue.x
            val startY = startValue.y
            val endX = endValue.x
            val endY = endValue.y
            val currentX = startX + (endX - startX) * fraction
            val currentY = startY + (endY - startY) * fraction
            return PointF(currentX, currentY)
        }

    }

}