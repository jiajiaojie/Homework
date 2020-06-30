package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewHashMap = mutableMapOf<Int, View?>()

    fun <T : View?>  getView(@IdRes id: Int): T?  {
        var view = viewHashMap[id]
        // TODO: 2020/6/30 判断等空的语法
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap.put(id, view)
        }
        // 强转
        // TODO: 2020/7/1 解除警告
        return view as? T?
    }

    fun setText(@IdRes id: Int, text: String?) {
        (getView(id) as TextView?)?.text = text
    }

}