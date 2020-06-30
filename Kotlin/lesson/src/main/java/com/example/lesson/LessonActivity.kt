package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.BaseView
import com.example.lesson.entity.Lesson

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {

    // 直接初始化方式
//    override var lessonPresenter: LessonPresenter = LessonPresenter(this)

    // 委托，使用 by 关键字，lazy 会在调用的时候才初始化，且只会初始化一次
    override val lessonPresenter: LessonPresenter by lazy {
        LessonPresenter(this)
    }

    private val lessonAdapter = LessonAdapter()

    lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        findViewById<RecyclerView>(R.id.list).let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = lessonAdapter
            it.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        }

        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            setOnRefreshListener {
                lessonPresenter.fetchData()
            }
            isRefreshing = true
        }

        lessonPresenter.fetchData()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        lessonPresenter.showPlayback()
        return false
    }

    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }
}