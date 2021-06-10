package com.grinaldi.moovi.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.grinaldi.moovi.databinding.ActivityFavoriteBinding
import com.grinaldi.moovi.ui.adapter.SectionFavoritePagerAdapter

class FavoriteActivity : AppCompatActivity() {
    companion object {
        val TAB_TITLES = arrayOf("Movies", "TV Shows")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)

        val viewPagerAdapter = SectionFavoritePagerAdapter(this)
        favoriteBinding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(
            favoriteBinding.tabs,
            favoriteBinding.viewPager
        ) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }
}