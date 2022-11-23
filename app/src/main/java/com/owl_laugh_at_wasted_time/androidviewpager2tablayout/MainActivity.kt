package com.owl_laugh_at_wasted_time.androidviewpager2tablayout

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpagerexample.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    val pager by lazy { findViewById<ViewPager2>(R.id.pager) }
    val tabLayout by lazy { findViewById<TabLayout>(R.id.tabLayout) }
    val skipButton by lazy { findViewById<Button>(R.id.skip) }
    val onwardsButton by lazy { findViewById<Button>(R.id.onwards) }
    val homeButton by lazy { findViewById<Button>(R.id.home) }
    val activButton by lazy { findViewById<Button>(R.id.activ) }
    val words = arrayListOf(
        "One",
        "Two",
        "Three",
        "Four",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewPager(words)
        setOnClickListeners()
        initActivAndHomeButton()
    }

    private fun initActivAndHomeButton() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == words.size - 1) {
                    skipButton.visibility = View.GONE
                    onwardsButton.visibility = View.GONE
                    activButton.visibility = View.VISIBLE
                    homeButton.visibility = View.VISIBLE
                } else {
                    skipButton.visibility = View.VISIBLE
                    onwardsButton.visibility = View.VISIBLE
                    activButton.visibility = View.GONE
                    homeButton.visibility = View.GONE
                }
            }
        })
    }

    private fun setViewPager(words: ArrayList<String>) {
        val pagerAdapter = PagerAdapter(this, words)
        pager.adapter = pagerAdapter
        pager.isUserInputEnabled = false
        TabLayoutMediator(tabLayout, pager) { tab, position ->
        }.attach()
    }

    private fun setOnClickListeners() {
        skipButton.setOnClickListener {
            finish()
        }
        onwardsButton.setOnClickListener {
            pager.setCurrentItem(getItem(1), true)
        }
        homeButton.setOnClickListener {

        }
        activButton.setOnClickListener {

        }
    }

    private fun getItem(i: Int): Int {
        return pager.getCurrentItem() + i
    }
}