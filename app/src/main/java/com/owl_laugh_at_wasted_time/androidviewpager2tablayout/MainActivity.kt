package com.owl_laugh_at_wasted_time.androidviewpager2tablayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpagerexample.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.owl_laugh_at_wasted_time.androidviewpager2tablayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val words = arrayListOf(
        "One",
        "Two",
        "Three",
        "Four",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewPager(words)
        initActivAndHomeButton()
        setOnClickListeners()
    }

    private fun initActivAndHomeButton() {
        with(binding) {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position == words.size - 1) {
                        showActivAndHomeButton()
                    } else {
                        hideActivAndHomeButton()
                    }
                }
            })
        }

    }

    private fun ActivityMainBinding.showActivAndHomeButton() {
        skip.visibility = View.GONE
        onwards.visibility = View.GONE
        activ.visibility = View.VISIBLE
        home.visibility = View.VISIBLE
    }

    private fun ActivityMainBinding.hideActivAndHomeButton() {
        skip.visibility = View.VISIBLE
        onwards.visibility = View.VISIBLE
        activ.visibility = View.GONE
        home.visibility = View.GONE
    }

    private fun setViewPager(words: ArrayList<String>) {
        val pagerAdapter = PagerAdapter(words)
        binding.pager.adapter = pagerAdapter
        binding.pager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
        }.attach()
    }

    private fun setOnClickListeners() {
        binding.skip.setOnClickListener {
            finish()
        }
        binding.onwards.setOnClickListener {
            binding.pager.setCurrentItem(getItem(1), true)
        }
        binding.home.setOnClickListener {

        }
        binding.activ.setOnClickListener {

        }
    }

    private fun getItem(i: Int): Int {
        return binding.pager.getCurrentItem() + i
    }
}