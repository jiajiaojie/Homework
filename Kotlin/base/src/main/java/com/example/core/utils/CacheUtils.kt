package com.example.core.utils

import android.content.Context
import com.example.core.BaseApplication
import com.example.core.R

object CacheUtils {

    val context = BaseApplication.currentApplication()

    val SP = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun save(key: String, value: String) {
        SP.edit().putString(key, value).apply()
    }

    fun get(key: String): String? {
        return SP.getString(key, null)
    }

}