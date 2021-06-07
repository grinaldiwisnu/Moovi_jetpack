package com.grinaldi.moovi.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grinaldi.moovi.databinding.FragmentMovieBinding
import com.grinaldi.moovi.ui.adapter.ListAdapter
import com.grinaldi.moovi.ui.detail.DetailActivity
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import com.grinaldi.moovi.utils.ViewModelFactory

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val adapter = ListAdapter()
            adapter.setListener {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_CONTENT, it.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
                this.context?.startActivity(intent)
            }

            viewModel.getAllMovies().observe(viewLifecycleOwner, { movies ->
                movies?.let { movieList ->
                    fragmentMovieBinding.progressBar.visibility = View.GONE
                    adapter.setMovies(movieList)
                }
            })

            with(fragmentMovieBinding.recyclerMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}