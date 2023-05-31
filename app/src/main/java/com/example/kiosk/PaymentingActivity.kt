package com.example.kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import java.lang.Thread.sleep

class PaymentingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paymenting)

        val paymentImageview = findViewById<ImageView>(R.id.paymentImageview)
        val textview = findViewById<TextView>(R.id.textView)

        if (payment.equals("card")) {
            paymentImageview.setImageResource(R.drawable.card)
            textview.text = "카드를"
        } else if (payment.equals("cash")){
            paymentImageview.setImageResource(R.drawable.cash)
            textview.text = "현금을"
        } else {
            paymentImageview.setImageResource(R.drawable.emblem_08)
        }

        val intent = Intent(applicationContext, PaymentFinishActivity::class.java)

        Handler().postDelayed({startActivity(intent)},10000)
    }
}