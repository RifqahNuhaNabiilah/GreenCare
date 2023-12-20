package com.example.projectgreencare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class PlantInformation : AppCompatActivity() {
    lateinit var back: ImageView
    lateinit var _rectangle_2: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_information)
        back = findViewById(R.id.back)
        _rectangle_2 = findViewById(R.id._rectangle_2)

        back.setOnClickListener{
            val nextScreen = Intent(this@PlantInformation, Menu::class.java)
            startActivity(nextScreen)
        }
        _rectangle_2.setOnClickListener{}
        val nextScreen = Intent(this@PlantInformation, Menu::class.java)
        startActivity(nextScreen)

    }
}



