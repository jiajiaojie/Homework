package com.example.core

import android.app.Application

class BaseApplication : Application() {

    companion object {
        private lateinit var currentApplication: Application
        fun currentApplication(): Application {
            return currentApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        currentApplication = this
    }
}