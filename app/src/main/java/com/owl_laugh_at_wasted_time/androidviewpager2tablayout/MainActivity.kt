package com.owl_laugh_at_wasted_time.androidviewpager2tablayout

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpagerexample.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    val pager by lazy { findViewById<ViewPager2>(R.id.pager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val words = arrayListOf(
            "One",
            "Two",
            "Three",
            "Four",
        )

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val skip = findViewById<Button>(R.id.skip)
        val onwards = findViewById<Button>(R.id.onwards)
        val home = findViewById<Button>(R.id.home)
        val activ = findViewById<Button>(R.id.activ)

        val pagerAdapter = PagerAdapter(this, words)

        pager.adapter = pagerAdapter

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                pager.isUserInputEnabled = !(state == SCROLL_STATE_DRAGGING && pager.currentItem == 0)
            }
        })

        skip.setOnClickListener {
            finish()
        }
        onwards.setOnClickListener {
            pager.setCurrentItem(getItem(1), true)
        }
        home.setOnClickListener {

        }
        activ.setOnClickListener {

        }
        //   pager.orientation = ViewPager2.ORIENTATION_VERTICAL
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            //  tab.text = "${position + 1}"
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == words.size - 1) {
                    skip.visibility = View.GONE
                    onwards.visibility = View.GONE
                    activ.visibility = View.VISIBLE
                    home.visibility = View.VISIBLE
                } else {
                    skip.visibility = View.VISIBLE
                    onwards.visibility = View.VISIBLE
                    activ.visibility = View.GONE
                    home.visibility = View.GONE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun getItem(i: Int): Int {
        return pager.getCurrentItem() + i
    }
}