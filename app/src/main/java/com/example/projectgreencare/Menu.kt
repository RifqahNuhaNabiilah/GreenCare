package com.example.projectgreencare

import android.content.Intent
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

        _rectangle_planttype = findViewById(R.id._rectangle_planttype)
        _rectangle_plantcare = findViewById(R.id._rectangle_plantcare)
        _rectangle_aboutus = findViewById(R.id._rectangle_aboutus)

        _rectangle_planttype.setOnClickListener{
        val nextScreen = Intent(this@Menu, PlantType::class.java)
        startActivity(nextScreen)
    }
        _rectangle_plantcare.setOnClickListener{
            val nextScreen = Intent(this@Menu, PlantCare::class.java)
            startActivity(nextScreen)
        }
        _rectangle_aboutus.setOnClickListener{
            val nextScreen = Intent(this@Menu, AboutUs::class.java)
            startActivity(nextScreen)
        }



}}