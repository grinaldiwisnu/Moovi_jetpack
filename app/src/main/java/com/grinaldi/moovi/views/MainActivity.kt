package com.grinaldi.moovi.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grinaldi.moovi.R
import com.grinaldi.moovi.databinding.ActivityMainBinding
import com.grinaldi.moovi.utils.SectionPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.title.text = getString(R.string.app_name)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}