package com.grinaldi.moovi.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.grinaldi.moovi.R
import com.grinaldi.moovi.databinding.ActivityDetailBinding
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import com.grinaldi.moovi.utils.ViewModelFactory


class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONTENT = "EXTRA_CONTENT"
        const val EXTRA_TYPE = "EXTRA_TYPE"

        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val contentId: Int = intent.getIntExtra(EXTRA_CONTENT, 0)
        val type: Int = intent.getIntExtra(EXTRA_TYPE, TYPE_MOVIE)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]


        binding.progressBar.visibility = View.VISIBLE
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.getMovieDetail(type, contentId).observe(this, { detail ->
            supportActionBar?.title = detail?.title
            binding.progressBar.visibility = View.GONE
            Glide.with(this).load("$IMAGE_BASE_URL${detail?.posterPath}")
                .placeholder(R.drawable.ic_baseline_error_24)
                .into(binding.movieDetailPoster)
            Glide.with(this).load("$IMAGE_BASE_URL${detail?.backdropPath}")
                .placeholder(R.drawable.ic_baseline_error_24)
                .into(binding.movieDetailBackdrop)
            binding.tvMovieTitle.text = detail?.title + " (${detail?.releaseDate})"
            binding.tvMovieTagLine.text = detail?.tagLine
            binding.tvMovieDetail.text = detail?.overview
            binding.tvDetailRating.text =
                StringBuilder("${detail?.voteAverage} / 10 (${detail?.voteCount} Votes)")
        })
    }
}