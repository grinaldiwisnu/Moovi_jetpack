package com.grinaldi.moovi.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grinaldi.moovi.databinding.FragmentTvBinding
import com.grinaldi.moovi.ui.adapter.ListAdapter
import com.grinaldi.moovi.ui.detail.DetailActivity
import com.grinaldi.moovi.utils.Constants.TYPE_TV_SHOW
import com.grinaldi.moovi.utils.ViewModelFactory

class TvFragment : Fragment() {
    private lateinit var fragmentTvBinding: FragmentTvBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            fragmentTvBinding.progressBar.visibility = View.VISIBLE
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            val adapter = ListAdapter()
            adapter.setListener {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_CONTENT, it.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_TV_SHOW)
                this.context?.startActivity(intent)
            }
            viewModel.getAllTvShows().observe(viewLifecycleOwner, { tvShows ->
                tvShows?.let { tvShowList ->
                    fragmentTvBinding.progressBar.visibility = View.GONE
                    adapter.setMovies(tvShowList)
                }
            })
            with(fragmentTvBinding.recyclerTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}