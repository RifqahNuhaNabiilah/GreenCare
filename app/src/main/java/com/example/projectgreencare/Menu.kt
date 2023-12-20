package com.example.projectgreencare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu : AppCompatActivity() {

    lateinit var _rectangle_planttype: View
    lateinit var _rectangle_plantcare: View
    lateinit var _rectangle_aboutus: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}