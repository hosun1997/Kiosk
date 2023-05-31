package com.example.kiosk

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.text.DecimalFormat

public var payment : String = ""

class PaymentActivity : AppCompatActivity() {
    lateinit var menuListView : ListView
    lateinit var rdbCard : RadioButton
    lateinit var rdbCash : RadioButton
    lateinit var cardSpinner : Spinner
    lateinit var cashSpinner : Spinner
    lateinit var paymentBtn : Button
    lateinit var basketAdapter :BasketListAdapter
    lateinit var clean : TextView
    lateinit var tvTotal : TextView
    lateinit var tvPrice : TextView

    var total: Int = 0
    var total2 : Int = 0
    val dec = DecimalFormat("#,###")



    val itemList1 = listOf<String>(
        "카드사를 선택하세요",
        "KB국민카드",
        "신한카드",
        "하나카드",
        "롯데카드",
        "BC카드",
        "NH농협카드",
        "삼성카드",
        "현대카드"
    )
    val itemList2 = listOf<String>(
        "은행을 선택하세요",
        "IBK기업은행",
        "NH농협은행",
        "국민은행",
        "신한은행",
        "우리은행",
        "KEB하나은행",
        "외환은행",
        "한국시티은행"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        menuListView = findViewById<ListView>(R.id.menuListview)
        basketAdapter = BasketListAdapter(this, basketList)
        clean = findViewById<TextView>(R.id.clean)
        rdbCard = findViewById<RadioButton>(R.id.rdbCard)
        rdbCash = findViewById<RadioButton>(R.id.rdbCash)
        cardSpinner = findViewById<Spinner>(R.id.cardSpinner)
        cashSpinner = findViewById<Spinner>(R.id.cashSpinner)
        paymentBtn = findViewById<Button>(R.id.paymentBtn)
        tvTotal = findViewById<TextView>(R.id.tvTotal)
        tvPrice = findViewById<TextView>(R.id.tvPrice)

        val adapter1: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList1)
        val adapter2: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList2)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cardSpinner.adapter = adapter1
        cashSpinner.adapter = adapter2

        rdbCard.setOnClickListener {
            cardSpinner.visibility = View.VISIBLE
            cashSpinner.visibility = View.INVISIBLE
        }

        rdbCash.setOnClickListener {
            cardSpinner.visibility = View.INVISIBLE
            cashSpinner.visibility = View.VISIBLE
        }

        if (basketList.isEmpty()) {
            clean.visibility = View.VISIBLE
        } else {
            clean.visibility = View.INVISIBLE
        }

        menuListView.adapter = basketAdapter

        menuListView.setOnItemClickListener { parent, view, position, id ->
            val dlg = AlertDialog.Builder(this)
            dlg.setTitle("해당 메뉴 선택")
            dlg.setMessage("해당 메뉴를 삭제하시겠습니까?")
            dlg.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->

            })
            dlg.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                total -= basketList[position].amount * basketList[position].price
                basketList.removeAt(position)
                if (basketList.isEmpty()) {
                    clean.visibility = View.VISIBLE
                }
                basketAdapter.notifyDataSetChanged()
                if (total == 0) {
                    paymentBtn.text = "메뉴를 골라주세요!!"
                } else {
                    paymentBtn.text = dec.format(total) + "원 결제하기"
                }
            })
            dlg.show()
        }

        if (basketList.isEmpty()) {
            paymentBtn.text = "메뉴를 골라주세요!"
        } else {
            for (i in 0..basketList.size - 1) {
                total += basketList[i].amount * basketList[i].price
                total2 += basketList[i].amount

                tvTotal.text = "주문 총량: " + total2.toString()
                tvPrice.text = dec.format(total) + "원"
                paymentBtn.text = dec.format(total) + "원 결제하기"
            }
        }


        paymentBtn.setOnClickListener {
            if (basketList.isEmpty()) {
                Toast.makeText(this, "메뉴를 골라주세요!!", Toast.LENGTH_SHORT).show()
            } else if (rdbCard.isChecked == false && rdbCash.isChecked == false) {
                Toast.makeText(this, "결제 수단을 선택해주세요!!", Toast.LENGTH_SHORT).show()
            } else if (rdbCard.isChecked && cardSpinner.selectedItemPosition == 0) {
                Toast.makeText(this, "카드사를 선택해주세요!!", Toast.LENGTH_SHORT).show()
            } else if (rdbCash.isChecked && cashSpinner.selectedItemPosition == 0) {
                Toast.makeText(this, "은행을 선택해주세요!!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(applicationContext, PaymentingActivity::class.java)
                if (rdbCard.isChecked) {
                    payment = "card"
                } else {
                    payment = "cash"
                }

                startActivity(intent)
            }
        }



    }
}