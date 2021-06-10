package com.grinaldi.moovi.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.grinaldi.moovi.R
import com.grinaldi.moovi.databinding.ActivityMainBinding
import com.grinaldi.moovi.ui.adapter.SectionPagerAdapter
import com.grinaldi.moovi.ui.favorite.FavoriteActivity

class MainActivity : AppCompatActivity() {
    companion object {
        val TAB_TITLES = arrayOf("Movies", "TV Shows")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeActivityBinding.root)

        setSupportActionBar(homeActivityBinding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val viewPagerAdapter = SectionPagerAdapter(this)
        homeActivityBinding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            homeActivityBinding.tabs,
            homeActivityBinding.viewPager
        ) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}