package io.jcal.theMovie.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.databinding.FragmentPopularShowsBinding
import io.jcal.theMovie.presentation.ui.adapter.ShowsAdapter
import io.jcal.theMovie.presentation.ui.home.viewmodel.TvShowsViewModel
import io.jcal.theMovie.utils.SpacingItemDecoration
import io.jcal.theMovie.utils.toTransitionGroup
import javax.inject.Inject

class PopularShowsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<TvShowsViewModel> { factory }
    private lateinit var binding: FragmentPopularShowsBinding
    private lateinit var adapter: ShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentPopularShowsBinding.inflate(
        inflater,
        container,
        false
    ).let {
        binding = it
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ShowsAdapter(
            showClickListener = { show, _, poster ->
                val extras = FragmentNavigatorExtras(
                    poster.toTransitionGroup()
                )
                findNavController()
                    .navigate(
                        PopularShowsFragmentDirections.popularShowsToShowDetail(
                            show.id,
                            show.posterPath
                        ), extras
                    )
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
        viewModel.tvShows.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    companion object {
        private const val SPACING = 16
    }
}
