package com.example.kiosk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

public var basketList = arrayListOf<Basket>(

)

class OrdercheckActivity : AppCompatActivity() {
    lateinit var cancelBtn : Button
    lateinit var finishBtn : Button
    lateinit var tabLayout : TabLayout
    lateinit var viewPager : ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordercheck)

        cancelBtn = findViewById<Button>(R.id.cancelBtn)
        finishBtn = findViewById<Button>(R.id.finishBtn)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager2>(R.id.viewPager)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 탭이 선택되었을 때
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 탭이 선택되지 않은 상태로 변경 되었을 때
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 이미 선택된 탭이 다시 선택되었을 때
            }
        })

        // 뷰페이저 어댑터 연결
        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            when(position) {
                0 -> tab.text = "한식"
                1 -> tab.text = "중식"
                2 -> tab.text = "일식"
                3 -> tab.text = "양식"
                4 -> tab.text = "야식"
                5 -> tab.text = "후식"
            }
        }.attach()

        cancelBtn.setOnClickListener {
            finish()
        }
        finishBtn.setOnClickListener {
            val intent = Intent(applicationContext, PaymentActivity::class.java)
            startActivity(intent)
        }
    }
}