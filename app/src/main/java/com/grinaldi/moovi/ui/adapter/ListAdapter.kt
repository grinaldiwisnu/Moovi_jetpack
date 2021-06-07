package com.grinaldi.moovi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.databinding.MovieItemListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MovieViewHolder>() {

    private var movies = ArrayList<MovieEntity>()
    private lateinit var listener: (MovieEntity) -> Unit

    fun setMovies(movies: List<MovieEntity>?) {
        movies?.let {
            this.movies.clear()
            this.movies.addAll(it)
            this.notifyDataSetChanged()
        }
    }

    fun setListener(listener: (MovieEntity) -> Unit) {
        this.listener = listener
    }

    inner class MovieViewHolder(private val binding: MovieItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                title.text = movie.title
                userScore.text = movie.voteAverage.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                    .into(moviePoster)
                itemView.setOnClickListener { listener(movie) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            MovieItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position])


    override fun getItemCount(): Int = movies.size
}