package com.grinaldi.moovi.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.grinaldi.moovi.App
import com.grinaldi.moovi.R
import com.grinaldi.moovi.data.sources.local.entity.DetailEntity
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.databinding.ActivityDetailBinding
import com.grinaldi.moovi.ui.adapter.MovieGenreAdapter
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import com.grinaldi.moovi.utils.Status
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONTENT = "EXTRA_CONTENT"
        const val EXTRA_TYPE = "EXTRA_TYPE"

        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @Inject
    lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailEntity: DetailEntity
    private lateinit var genreAdapter: MovieGenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val contentId: Int = intent.getIntExtra(EXTRA_CONTENT, 0)
        val type: Int = intent.getIntExtra(EXTRA_TYPE, TYPE_MOVIE)
        var isMovieFavorite = false

        genreAdapter = MovieGenreAdapter()

        with(binding.rvMovieGenre) {
            layoutManager = LinearLayoutManager(
                this@DetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = genreAdapter
        }

        val data = viewModel.let {
            if (type == TYPE_MOVIE) it.getMovieDetail(contentId)
            else it.getTvShowDetail(contentId)
        }

        data.observe(this, { detail ->
            when (detail.status) {
                Status.SUCCESS -> {
                    binding.progressBarDetail.visibility = View.GONE
                    binding.errorMessage.visibility = View.GONE

                    detail.data?.let {
                        supportActionBar?.title = it.title
                        binding.progressBarDetail.visibility = View.GONE
                        Glide.with(this).load("$IMAGE_BASE_URL${it.posterPath}")
                            .placeholder(R.drawable.ic_baseline_error_24)
                            .into(binding.smallPoster)
                        Glide.with(this).load("$IMAGE_BASE_URL${it.posterPath}")
                            .placeholder(R.drawable.ic_baseline_error_24)
                            .into(binding.ivBigPoster)
                        genreAdapter.setGenres(it.genres)
                        binding.rvMovieGenre.adapter = genreAdapter
                        binding.tvMovieTitle.text = it.title
                        binding.tvMovieTagLine.text = it.tagLine
                        binding.tvOverview.text = it.overview
                        binding.tvDetailHomePage.text = it.homepage
                        binding.tvDetailRating.text =
                            StringBuilder("${it.voteAverage} / 10")
                        binding.cardOverview.visibility = View.VISIBLE
                        binding.cvPoster.visibility = View.VISIBLE
                        binding.progressBarDetail.visibility = View.GONE
                    }

                    detailEntity = detail.data as DetailEntity
                }
                Status.LOADING -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBarDetail.visibility = View.GONE
                    binding.cardOverview.visibility = View.GONE
                    binding.cvPoster.visibility = View.GONE
                    binding.fab.visibility = View.GONE
                    binding.errorMessage.apply {
                        visibility = View.VISIBLE
                        text = detail.message
                    }
                }
            }
        })

        viewModel.checkIsMovieFavorite(contentId).observe(this, {
            isMovieFavorite = it
            val icon =
                if (it) R.drawable.ic_baseline_thumb_up_alt_24 else R.drawable.ic_baseline_thumb_down_alt_24
            binding.fab.setImageResource(icon)
        })

        binding.fab.setOnClickListener {
            val movie = mapDetailToMovie(detailEntity, type)
            if (isMovieFavorite) {
                viewModel.deleteMovieFromFavorite(movie)
                Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addMovieToFavorite(movie)
                Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mapDetailToMovie(detailEntity: DetailEntity, movieType: Int): MovieEntity {
        return MovieEntity(
            detailEntity.id,
            detailEntity.overview,
            detailEntity.title,
            detailEntity.posterPath,
            detailEntity.voteAverage,
            movieType
        )
    }
}