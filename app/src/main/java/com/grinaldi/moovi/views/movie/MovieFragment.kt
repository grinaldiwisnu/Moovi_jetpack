package com.grinaldi.moovi.views.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grinaldi.moovi.databinding.FragmentMovieBinding
import com.grinaldi.moovi.views.adapter.ListAdapter

class MovieFragment : Fragment() {
    companion object {
        const val CLICK_MOVIE = 1
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]

        if (activity != null) {
            val movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movie = movieViewModel.getMovies()
            val adapter = ListAdapter(CLICK_MOVIE)
            adapter.setAll(movie)

            binding.rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}