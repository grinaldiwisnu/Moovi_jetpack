package com.grinaldi.moovi.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grinaldi.moovi.databinding.ActivityMainBinding
import com.grinaldi.moovi.ui.adapter.SectionPagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeActivityBinding.root)

        val viewPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        homeActivityBinding.viewPager.adapter = viewPagerAdapter
        homeActivityBinding.tabs.setupWithViewPager(homeActivityBinding.viewPager)
    }
}