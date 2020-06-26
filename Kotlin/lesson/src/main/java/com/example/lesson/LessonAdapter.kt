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

    class LessonViewHolder : BaseViewHolder {

        constructor(itemView: View) : super(itemView) {

        }

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_lesson, parent, false))
            }
        }

        fun onBind(lesson: Lesson) {
            var date = lesson.date
            if (date == null) {
                date = "日期待定"
            }
            setText(R.id.tv_date, date)
            setText(R.id.tv_content, lesson.content)
            val state = lesson.state;
            if (state != null) {
                setText(R.id.tv_state, state.stateName())
                var colorRes = when (state) {
                    PLAYBACK -> R.color.playback
                    LIVE -> R.color.live
                    WAIT -> R.color.wait
                    else -> R.color.playback
                }
                val backgroundColor = itemView.context.getColor(colorRes)
                getView<View>(R.id.tv_state)?.setBackgroundColor(backgroundColor)
            }
        }
    }
}