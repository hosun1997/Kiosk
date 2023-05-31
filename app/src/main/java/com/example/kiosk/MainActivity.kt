package com.example.kiosk

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var imageBtn1: ImageButton
    lateinit var imageBtn2: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(applicationContext, OrdercheckActivity::class.java)

        imageBtn1 = findViewById<ImageButton>(R.id.imageBtn1)
        imageBtn2 = findViewById<ImageButton>(R.id.imageBtn2)

        imageBtn1.setOnClickListener {
            startActivity(intent)
        }

        imageBtn2.setOnClickListener {
            startActivity(intent)
        }

    }
}