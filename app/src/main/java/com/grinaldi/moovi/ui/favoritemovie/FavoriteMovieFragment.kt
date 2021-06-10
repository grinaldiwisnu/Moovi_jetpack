package com.grinaldi.moovi.ui.favoritemovie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.grinaldi.moovi.App
import com.grinaldi.moovi.databinding.FragmentFavoriteMovieBinding
import com.grinaldi.moovi.ui.adapter.ListAdapter
import com.grinaldi.moovi.ui.detail.DetailActivity
import com.grinaldi.moovi.utils.Constants.TYPE_MOVIE
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {
    companion object {
        const val MOVIE_TYPE = "MOVIE_TYPE"
    }

    @Inject
    lateinit var viewModel: FavoriteMovieViewModel

    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieType = arguments?.getInt(MOVIE_TYPE)

        val adapter = ListAdapter {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_CONTENT, it.id)
            intent.putExtra(DetailActivity.EXTRA_TYPE, movieType)
            this.context?.startActivity(intent)
        }

        with(binding.recyclerMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        val data = viewModel.let {
            if (movieType == TYPE_MOVIE) it.getFavoriteMovieList()
            else it.getFavoriteTvShowList()
        }

        data.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(it)
        })

        viewModel.getErrorMessage().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
                binding.errorImage.visibility = View.VISIBLE
                binding.textSad.text = it
            } else {
                binding.progressBar.visibility = View.GONE
                binding.errorImage.visibility = View.GONE
                binding.textSad.visibility = View.GONE
            }
        })
    }
}