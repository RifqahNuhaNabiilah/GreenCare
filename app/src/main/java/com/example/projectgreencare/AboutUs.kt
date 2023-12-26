package com.example.projectgreencare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AboutUs : AppCompatActivity() {
    lateinit var _rectangle_2:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        _rectangle_2 = findViewById(R.id._rectangle_2)

        _rectangle_2.setOnClickListener{
            val nextScreen = Intent(this@AboutUs, Menu::class.java)
            startActivity(nextScreen)
        }
    }
}