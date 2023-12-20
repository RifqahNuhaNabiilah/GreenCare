package com.example.projectgreencare

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private lateinit var _rectangle_1: ImageButton
    private lateinit var _rectangle_2: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _rectangle_1 = findViewById(R.id._rectangle_1)
        _rectangle_2 = findViewById(R.id._rectangle_2)

        _rectangle_1.setOnClickListener(View.OnClickListener {
            val nextScreen = Intent(this@MainActivity, SignUp::class.java)
            startActivity(nextScreen)
        })

        _rectangle_2.setOnClickListener(View.OnClickListener {
            val nextScreen = Intent(this@MainActivity, Login::class.java)
            startActivity(nextScreen)
        })
    }
}
