package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.util.*

class LessonPresenter {

    companion object {
        const val LESSON_PATH = "lessons"
        val type = object : TypeToken<List<Lesson>>(){}.type
    }

    val activity: LessonActivity

    var lessons = listOf<Lesson>()

    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>>{

            override fun onSuccess(entity: List<Lesson>) {
                // 内部类使用外部类的引用
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread {
                    activity.showResult(entity)
                }
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread {
                    toast(message)
                }
            }

        })
    }

    fun showPlayback() {
        val playbackLessons: MutableList<Lesson> = ArrayList()
        // for each
        for (lesson in lessons) {
            if (lesson.state === Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }
}