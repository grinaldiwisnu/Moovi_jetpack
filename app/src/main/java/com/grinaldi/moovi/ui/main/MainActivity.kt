package com.grinaldi.moovi.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.grinaldi.moovi.databinding.ActivityMainBinding
import com.grinaldi.moovi.ui.adapter.SectionPagerAdapter

class MainActivity : AppCompatActivity() {
    companion object {
        val TAB_TITLES = arrayOf("Movies", "TV Shows")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeActivityBinding.root)

        val viewPagerAdapter = SectionPagerAdapter(this)
        homeActivityBinding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            homeActivityBinding.tabs,
            homeActivityBinding.viewPager
        ) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }
}