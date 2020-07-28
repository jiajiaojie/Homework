package com.example.customview.edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.postDelayed
import com.example.customview.R

class LabelEditTextActivity : AppCompatActivity() {

    private lateinit var labelEditText: LabelEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_label_edit_text)
        labelEditText = findViewById(R.id.edit_text)

//        labelEditText.postDelayed(3000) {
//            labelEditText.useLabel = false
//        }

//        labelEditText.postDelayed({
//            labelEditText.useLabel = false
//        }, 2000)
//
//        labelEditText.postDelayed(object : Runnable {
//            override fun run() {
//
//            }
//        }, 200)
    }
}