package com.example.core

interface BaseView<T> {

    // 抽象属性
    // var 需要重写 get 和 set; val 只需要重写 get
    val lessonPresenter: T
}