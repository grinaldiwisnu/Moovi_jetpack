package com.grinaldi.moovi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grinaldi.moovi.databinding.GenreItemListBinding

class MovieGenreAdapter : RecyclerView.Adapter<MovieGenreAdapter.GenreViewHolder>() {
    private var genreList: List<String> = listOf()

    fun setGenres(genreList: List<String>) {
        this.genreList = genreList
        this.notifyDataSetChanged()
    }

    inner class GenreViewHolder(private val view: GenreItemListBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(genre: String) {
            view.genre.text = genre
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieGenreAdapter.GenreViewHolder {
        val view = GenreItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieGenreAdapter.GenreViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun getItemCount(): Int = genreList.size
}