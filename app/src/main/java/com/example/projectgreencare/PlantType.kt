package com.example.projectgreencare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class PlantType : AppCompatActivity() {
    lateinit var back: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_type)

                back = findViewById(R.id.back)

                back.setOnClickListener{
                    val nextScreen = Intent(this@PlantType, Menu::class.java)
                    startActivity(nextScreen)
                }

            }
        }