package com.grinaldi.moovi.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grinaldi.moovi.ui.favoritemovie.FavoriteMovieFragment
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import com.grinaldi.moovi.utils.Constants.TYPE_TV_SHOW

class SectionFavoritePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FavoriteMovieFragment()
        val type = when (position) {
            0 -> TYPE_MOVIE
            1 -> TYPE_TV_SHOW
            else -> TYPE_MOVIE
        }
        fragment.arguments = Bundle().apply {
            putInt(FavoriteMovieFragment.MOVIE_TYPE, type)
        }
        return fragment
    }
}