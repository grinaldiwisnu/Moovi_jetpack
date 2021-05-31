package com.grinaldi.moovi.views

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.grinaldi.moovi.R
import com.grinaldi.moovi.data.models.Movie
import com.grinaldi.moovi.databinding.ActivityDetailBinding
import com.grinaldi.moovi.utils.DetailBinding
import com.grinaldi.moovi.views.movie.MovieFragment.Companion.CLICK_MOVIE
import com.grinaldi.moovi.views.movie.MovieViewModel
import com.grinaldi.moovi.views.tv.TvFragment.Companion.CLICK_TV_SHOW
import com.grinaldi.moovi.views.tv.TvViewModel

class DetailActivity : AppCompatActivity(), DetailBinding {
    companion object {
        const val ID = "id"
        const val CLICK = "click"
        const val TITLE = "title"
    }

    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        collapseToolbarConfiguration()

        val movieViewModel = ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
        val tvShowViewModel = ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory())[TvViewModel::class.java]

        when (intent.getIntExtra(CLICK, 0)) {
            CLICK_MOVIE -> setBinding(movieViewModel.detailMovie(intent.getIntExtra(ID, 0))!!)
            CLICK_TV_SHOW -> setBinding(tvShowViewModel.detailTvShow(intent.getIntExtra(ID, 0))!!)
        }
    }

    private fun collapseToolbarConfiguration() {
        collapsingToolbar = findViewById(R.id.collapseToolbar)
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (binding.collapseToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbar)) {
                binding.collapseToolbar.setCollapsedTitleTextColor(Color.BLACK)
                binding.toolbar.apply {
                    setBackgroundColor(Color.WHITE)
                    title = intent.getStringExtra(TITLE)
                    visibility = View.VISIBLE
                }
            } else {
                binding.collapseToolbar.setExpandedTitleColor(Color.BLACK)
                binding.toolbar.setBackgroundColor(Color.TRANSPARENT)
                binding.toolbar.visibility = View.GONE
            }
        })
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun setBinding(movie: Movie) {
        multipleGlide(binding.ivBigPoster, binding.ivSmallPoster, movie)
        binding.tvTitle.text = movie.title + " (${movie.date})"
        binding.tvScore.text = movie.rating + "/100"
        binding.tvOverview.text = movie.description
    }

    override fun multipleGlide(firstImage: ImageView, secondImage: ImageView, movie: Movie) {
        Glide.with(this).load(movie.image).into(firstImage)
        Glide.with(this).load(movie.image).into(secondImage)
    }
}