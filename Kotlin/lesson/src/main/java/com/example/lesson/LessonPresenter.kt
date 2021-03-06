package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken

class LessonPresenter(var activity: LessonActivity) {

    companion object {
        const val LESSON_PATH = "lessons"
        val type = object : TypeToken<List<Lesson>>() {}.type
    }

    var lessons = listOf<Lesson>()

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {

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
//        var playbackLessons: MutableList<Lesson> = ArrayList()
        // 1
//        for (lesson in lessons) {
//            if (lesson.state === Lesson.State.PLAYBACK) {
//                playbackLessons.add(lesson)
//            }
//        }
        // 2 forEach
//        lessons.forEach {
//            if (it.state === Lesson.State.PLAYBACK) {
//                playbackLessons.add(it)
//            }
//        }
        // 3 filter
        val playbackLessons = lessons.filter {
            it.state === Lesson.State.PLAYBACK
        }
        activity.showResult(playbackLessons)
    }
}