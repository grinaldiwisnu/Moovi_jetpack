package com.grinaldi.moovi.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.grinaldi.moovi.App
import com.grinaldi.moovi.databinding.FragmentMovieBinding
import com.grinaldi.moovi.ui.adapter.ListAdapter
import com.grinaldi.moovi.ui.detail.DetailActivity
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import com.grinaldi.moovi.utils.Status
import javax.inject.Inject

class MovieFragment : Fragment() {
    @Inject
    lateinit var viewModel: MovieViewModel

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (context?.applicationContext as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            binding.progressBar.visibility = View.VISIBLE
            binding.constrainDataNotFound.visibility = View.GONE

            val adapter = ListAdapter {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_CONTENT, it.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
                this.context?.startActivity(intent)
            }

            val data = viewModel.getAllMovies()

            data.observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        adapter.submitList(it.data)
                        binding.errorImage.visibility = View.GONE
                        binding.textSadMovie.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.errorImage.visibility = View.VISIBLE
                        binding.textSadMovie.text = it.message
                        binding.textSadMovie.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                }
            })

            with(binding.recyclerMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}