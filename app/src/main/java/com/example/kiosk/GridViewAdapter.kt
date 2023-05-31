package com.example.kiosk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.DecimalFormat

class GridViewAdapter(val context: Context, val foodList: ArrayList<Food>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_foodmenu, null)

        val photoFood = view.findViewById<ImageView>(R.id.photoFood)
        val nameFood = view.findViewById<TextView>(R.id.nameFood)
        val priceFood = view.findViewById<TextView>(R.id.priceFood)
        val dec =DecimalFormat("#,###")

        val food =foodList[position]
        val resourceId =context.resources.getIdentifier(food.photo, "drawable", context.packageName)
        photoFood.setImageResource(resourceId)
        nameFood.text = food.name
        priceFood.text = dec.format(food.price.toInt()) + "원"

        return view
    }

    override fun getCount(): Int {
        return foodList.size
    }

    override fun getItem(position: Int): Any {
        return foodList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}