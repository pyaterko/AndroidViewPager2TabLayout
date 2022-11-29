package com.owl_laugh_at_wasted_time.androidviewpager2tablayout

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.viewpagerexample.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.owl_laugh_at_wasted_time.androidviewpager2tablayout.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val words = arrayListOf(
        "One",
        "Two если сделать для одного из слайдов текст подлиннее - он не вмещается на страницу",
        "Three",
        "Four",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewPager(words)
        setButton()

        binding.bottomButton.setListener { action ->
            if (action == BottomButtonAction.POSITIVE) {
                binding.bottomButton.isProgressMode = true
                lifecycleScope.launch {
                    delay(1000)
                    binding.bottomButton.isProgressMode = false
                    binding.pager.setCurrentItem(getItem(1), true)
                }
            } else if (action == BottomButtonAction.NEGATIVE) {
                Toast.makeText(this, "Пропустить", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setButton() {
        with(binding) {
            bottomButton.setPositiveButtonText("ДАЛЕЕ")
            bottomButton.setNegativeButtonText("ПРОПУСТИТЬ")
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position == words.size - 1) {
                        binding.bottomButton.replaceListener { action ->
                            if (action == BottomButtonAction.POSITIVE) {
                                Toast.makeText(this@MainActivity, "На главную", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                Toast.makeText(this@MainActivity, "Активировать", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                        binding.bottomButton.setPositiveButtonText("НА ГЛАВНУЮ")
                        binding.bottomButton.setNegativeButtonText("АКТИВИРОВАТЬ")
                    } else {
                        binding.bottomButton.setPositiveButtonText("ДАЛЕЕ")
                        binding.bottomButton.setNegativeButtonText("ПРОПУСТИТЬ")
                    }
                }
            })
        }

    }

    private fun setViewPager(words: ArrayList<String>) {
        val pagerAdapter = PagerAdapter()
        pagerAdapter.words = words
        binding.pager.adapter = pagerAdapter
        binding.pager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.view.isClickable = false
        }.attach()
    }


    private fun getItem(i: Int): Int {
        return binding.pager.getCurrentItem() + i
    }
}