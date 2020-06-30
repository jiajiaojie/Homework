package com.example.core

import android.app.Application

class BaseApplication : Application() {

    companion object {
        lateinit var currentApplication: Application
            // 禁止外部修改
            private set
    }

    override fun onCreate() {
        super.onCreate()
        currentApplication = this
    }
}