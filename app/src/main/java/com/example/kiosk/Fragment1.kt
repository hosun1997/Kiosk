package com.example.kiosk

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text
import java.util.Locale


class Fragment1 : Fragment() {

    lateinit var ordercheckActivity: OrdercheckActivity
    lateinit var tts: TextToSpeech

    override fun onAttach(context: Context) {
        super.onAttach(context)

        ordercheckActivity = context as OrdercheckActivity
    }

    var foodList = arrayListOf<Food>(
        Food("김치찌개", "매콤한 김치찌개, 공기밥 포함", "9900", "kimch"),
        Food("찜닭", "간장과 고추장의 황금 비율! 국산 냉장육11호를 사용합니다.", "16000", "steamed"),
        Food("된장찌개", "찌개, 반찬, 공기밥 구성!", "8400", "soybean"),
        Food("비빔밥", "영양만점 비빔밥", "8500", "bibimbab"),
        Food("제육볶음", "국내산 돼지고기 듬~뿍 제육볶음", "7500", "meet")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_1, null)

        val gridView: GridView = view.findViewById<GridView>(R.id.gridView)

        val foodAdapter = context?.let { GridViewAdapter(it, foodList) }
        gridView.adapter = foodAdapter

        gridView.setOnItemClickListener { parent, view, position, id ->
            val dlg = AlertDialog.Builder(ordercheckActivity)
            val dialogView = View.inflate(ordercheckActivity, R.layout.dialog, null)


            val dialogPhoto = dialogView.findViewById<ImageView>(R.id.dialogPhoto)
            val resourceId = context?.resources?.getIdentifier(
                foodList[position].photo,
                "drawable",
                requireContext().packageName
            )

            val summary = dialogView.findViewById<TextView>(R.id.summary)
            val ttsBtn = dialogView.findViewById<ImageButton>(R.id.ttsBtn)
            val btnPlus = dialogView.findViewById<ImageButton>(R.id.amountPlus)
            val btnMinus = dialogView.findViewById<ImageButton>(R.id.amountMinus)
            val amount = dialogView.findViewById<TextView>(R.id.amount)
            var count: Int = 1


            resourceId?.let { dialogPhoto.setImageResource(it) }
            dlg.setTitle(foodList[position].name)
            summary.text = foodList[position].summary
            dlg.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->

            })
            dlg.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                basketList.add(
                    Basket(
                        foodList[position].name,
                        count,
                        foodList[position].price.toInt()
                    )
                )
            })
            dlg.setView(dialogView)
            dlg.show()

            btnPlus.setOnClickListener {
                count++
                amount.setText(count.toString())
            }
            btnMinus.setOnClickListener {
                if (count < 2)
                    count = 1
                else
                    count--
                amount.setText(count.toString())
            }

            tts = TextToSpeech(ordercheckActivity, TextToSpeech.OnInitListener() {
                if (it == TextToSpeech.SUCCESS) {
                    val result = tts!!.setLanguage(Locale.KOREAN)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        return@OnInitListener
                    }
                }
            })

            ttsBtn.setOnClickListener {
                tts.speak(summary.text.toString(), TextToSpeech.QUEUE_FLUSH, null)
                tts.playSilentUtterance(750, TextToSpeech.QUEUE_ADD, null)
            }

        }
        return view
    }

}