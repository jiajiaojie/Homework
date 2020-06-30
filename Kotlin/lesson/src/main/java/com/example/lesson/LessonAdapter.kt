package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson
import com.example.lesson.entity.Lesson.State.PLAYBACK
import com.example.lesson.entity.Lesson.State.LIVE
import com.example.lesson.entity.Lesson.State.WAIT
import java.util.ArrayList

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var list: List<Lesson> = ArrayList()

    fun updateAndNotify(list: List<Lesson>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list.get(position))
    }

    class LessonViewHolder(itemView: View) : BaseViewHolder(itemView) {

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_lesson, parent, false))
            }
        }

        fun onBind(lesson: Lesson) {
            setText(R.id.tv_date, lesson.date ?: "日期待定")
            setText(R.id.tv_content, lesson.content)

            // 用 let 进行非空判断
            lesson.state?.let{
                setText(R.id.tv_state, it.stateName())
                val colorRes = when (it) {
                    PLAYBACK -> R.color.playback
                    LIVE -> R.color.live
                    WAIT -> R.color.wait
                }
                val backgroundColor = itemView.context.getColor(colorRes)
                getView<View>(R.id.tv_state)?.setBackgroundColor(backgroundColor)
            }
        }
    }
}