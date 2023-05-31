package com.example.kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.lang.Thread.sleep
import kotlin.random.Random

class PaymentFinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_finish)

        val orderNum = findViewById<TextView>(R.id.orderTextView)
        val random = Random
        orderNum.text = random.nextInt(1000).toString()

        val intent = Intent(this, MainActivity::class.java)
        basketList.removeAll(basketList)
        Handler().postDelayed({finishAffinity();startActivity(intent)},5000)
    }
}