package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentPopularShowsBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.presentation.mapper.model.TvShowUIList
import io.jcal.theMovie.presentation.ui.PopularShowsFragmentDirections.popularShowsToShowDetail
import io.jcal.theMovie.presentation.ui.adapter.ShowAdapter
import io.jcal.theMovie.presentation.viewmodel.TvShowsViewModel
import io.jcal.theMovie.utils.SpacingItemDecoration
import javax.inject.Inject

class PopularShowsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TvShowsViewModel> { viewModelFactory }
    private lateinit var binding: FragmentPopularShowsBinding
    private lateinit var adapter: ShowAdapter
    private var recyclerInstanceState: TvShowUIList? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_shows, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ShowAdapter(
            showClickListener = { show, _, rootView ->
                rootView.findNavController().navigate(popularShowsToShowDetail(show.id))
            }
        )
        binding.popularShowsRv.adapter = adapter
        binding.popularShowsRv.addItemDecoration(
            SpacingItemDecoration(
                requireContext(),
                SPACING
            )
        )
        binding.popularShowsRv.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.popularTvShows().observe(viewLifecycleOwner, Observer { response ->
            when (response.state) {
                SUCCESS -> {
                    if (recyclerInstanceState == null) {
                        adapter.addAll(response.results)
                    }
                }
            }
        })
    }

    companion object {
        private const val SPACING = 16
    }
}
