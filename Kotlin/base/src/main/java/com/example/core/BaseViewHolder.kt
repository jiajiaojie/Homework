package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView)

    val viewHashMap = mutableMapOf<Int, View?>()

    fun <T : View?>  getView(@IdRes id: Int): T?  {
        var view = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap.put(id, view)
        }
        // 强转
        return view as T?
    }

    fun setText(@IdRes id: Int, text: String) {
        (getView(id) as TextView?)?.text = text
    }

}