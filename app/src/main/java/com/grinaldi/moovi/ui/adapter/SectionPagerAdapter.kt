package com.grinaldi.moovi.ui.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.grinaldi.moovi.R
import com.grinaldi.moovi.ui.movie.MovieFragment
import com.grinaldi.moovi.ui.tv.TvFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitles = intArrayOf(R.string.movie, R.string.tv_show)

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> MovieFragment()
                1 -> TvFragment()
                else -> Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(tabTitles[position])

    override fun getCount(): Int = 2
}