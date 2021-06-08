package com.grinaldi.moovi.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grinaldi.moovi.ui.movie.MovieFragment
import com.grinaldi.moovi.ui.tv.TvFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MovieFragment()
        1 -> TvFragment()
        else -> Fragment()
    }
}