package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.toast
import com.example.lesson.LessonActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val usernameKey: String = "username"
    private val passwordKey: String = "password"

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var et_code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))

        findViewById<Button>(R.id.btn_login).setOnClickListener(this)
        findViewById<CodeView>(R.id.code_view).setOnClickListener(this)
    }


    override fun onClick(v: View?) {
//        (v as? CodeView)?.updateCode()
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val code = et_code.text.toString()

        val user = User(username, password, code)

        // 嵌套函数必须写在调用处的前面
        fun verify(): Boolean {
            // ?: 的用法
            // ?. 和 ?: 经常配合使用
            if (user.username?.length ?: 0 < 4) {
                toast("用户名不合法")
                return false
            }
            if (user.password?.length ?: 0 < 4) {
                toast("密码不合法")
                return false
            }
            return true
        }

        if (verify()) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            // 获取 class 的方法
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

}