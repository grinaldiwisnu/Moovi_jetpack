package com.grinaldi.moovi.views.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grinaldi.moovi.databinding.FragmentTvBinding
import com.grinaldi.moovi.views.adapter.ListAdapter

class TvFragment : Fragment() {
    companion object {
        const val CLICK_TV_SHOW = 2
    }

    private lateinit var viewModel: TvViewModel
    private lateinit var binding: FragmentTvBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvViewModel::class.java]

        if (activity != null) {
            val tvShowViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvViewModel::class.java]
            val tvShow = tvShowViewModel.getTvShow()
            val adapter = ListAdapter(CLICK_TV_SHOW)
            adapter.setAll(tvShow)

            binding.rvTvShow.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}