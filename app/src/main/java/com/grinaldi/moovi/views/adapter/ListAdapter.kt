package com.grinaldi.moovi.views.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grinaldi.moovi.data.models.Movie
import com.grinaldi.moovi.databinding.MovieItemListBinding
import com.grinaldi.moovi.views.DetailActivity
import com.grinaldi.moovi.views.DetailActivity.Companion.CLICK
import com.grinaldi.moovi.views.DetailActivity.Companion.ID
import com.grinaldi.moovi.views.DetailActivity.Companion.TITLE

class ListAdapter(private val adapterId: Int) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var catalogue = ArrayList<Movie>()

    fun setAll(data: List<Movie>) {
        catalogue.clear()
        catalogue.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
                MovieItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = catalogue.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val catalogue = catalogue[position]
        holder.bind(catalogue)
    }

    inner class ListViewHolder(private val binding: MovieItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(catalogue: Movie) {
            itemView.apply {
                Glide.with(itemView)
                        .load(catalogue.image)
                        .into(binding.imageContent)
                binding.textTitle.text = catalogue.title
                itemView.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(ID, catalogue.id)
                        putExtra(TITLE, catalogue.title)
                        putExtra(CLICK, adapterId)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}