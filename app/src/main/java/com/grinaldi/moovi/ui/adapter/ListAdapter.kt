package com.grinaldi.moovi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grinaldi.moovi.data.sources.local.entity.MovieEntity
import com.grinaldi.moovi.databinding.MovieItemListBinding

class ListAdapter(
    private val listener: (movie: MovieEntity) -> Unit
) :
    PagedListAdapter<MovieEntity, ListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieEntity> =
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(old: MovieEntity, new: MovieEntity): Boolean {
                    return old.title == new.title && old.overview == new.overview
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(old: MovieEntity, new: MovieEntity): Boolean {
                    return old == new
                }
            }
    }

    inner class MovieViewHolder(private val view: MovieItemListBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(movie: MovieEntity) {
            with(view) {
                title.text = movie.title
                userScore.text = movie.voteAverage.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                    .into(moviePoster)
            }
            itemView.setOnClickListener { listener(movie) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAdapter.MovieViewHolder {
        val view = MovieItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as MovieEntity)
    }
}