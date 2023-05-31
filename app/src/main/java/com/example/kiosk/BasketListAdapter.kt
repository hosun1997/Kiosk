package com.example.kiosk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.DecimalFormat

class BasketListAdapter(val context: Context, val basketList: ArrayList<Basket>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_basketmenu, null)

        val bname = view.findViewById<TextView>(R.id.bFoodName)
        val bamount = view.findViewById<TextView>(R.id.bFoodAmount)
        val bprice = view.findViewById<TextView>(R.id.bFoodPrice)
        val basket = basketList[position]
        val dec = DecimalFormat("#,###")


        bname.text = basket.name
        bamount.text = basket.amount.toString()
        bprice.text = dec.format((basket.amount * basket.price)).toString()
        return view
    }

    override fun getItem(position: Int): Any {
        return basketList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return basketList.size
    }


}