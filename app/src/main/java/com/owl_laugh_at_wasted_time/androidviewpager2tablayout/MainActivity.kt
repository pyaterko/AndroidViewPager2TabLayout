package com.owl_laugh_at_wasted_time.androidviewpager2tablayout

import android.os.Bundle
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
        initActivAndHomeButton()

        binding.bottomButton.setPositiveButtonText("Далее")
        binding.bottomButton.setNegativeButtonText("Пропустить")

        binding.bottomButton.setListener { action ->
            if (action == BottomButtonAction.POSITIVE &&
                binding.bottomButton.getPositiveButtonText() == "Далее"
            ) {
                binding.bottomButton.isProgressMode = true
                lifecycleScope.launch {
                    delay(1000)
                    binding.bottomButton.isProgressMode = false
                    binding.pager.setCurrentItem(getItem(1), true)
                }
            } else if (action == BottomButtonAction.POSITIVE &&
                binding.bottomButton.getPositiveButtonText() == "На главную"
            ) {
                finish()
            }
        }
    }

    private fun initActivAndHomeButton() {
        with(binding) {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position == words.size - 1) {
                        binding.bottomButton.setPositiveButtonText("На главную")
                        binding.bottomButton.setNegativeButtonText("Активировать")
                    } else {
                        binding.bottomButton.setPositiveButtonText("Далее")
                        binding.bottomButton.setNegativeButtonText("Пропустить")
                    }
                }
            })
        }

    }

    private fun setViewPager(words: ArrayList<String>) {
        val pagerAdapter = PagerAdapter(words)
        binding.pager.adapter = pagerAdapter
        binding.pager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
        }.attach()
    }


    private fun getItem(i: Int): Int {
        return binding.pager.getCurrentItem() + i
    }
}