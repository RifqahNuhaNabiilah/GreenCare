package com.example.projectgreencare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView


class PlantType : AppCompatActivity() {
    lateinit var back: ImageView
    lateinit var rectangle_2: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_type)

                back = findViewById(R.id.back)
        rectangle_2 = findViewById(R.id.rectangle_2)

                back.setOnClickListener{
                    val nextScreen = Intent(this@PlantType, Menu::class.java)
                    startActivity(nextScreen)
                }
        rectangle_2.setOnClickListener {
            val nextScreen = Intent(this@PlantType, PlantInformation::class.java)
            startActivity(nextScreen)
        }

            }
        }