package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentPopularMoviesListBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.presentation.mapper.model.MovieUIModelList
import io.jcal.theMovie.presentation.ui.adapter.MovieAdapter
import io.jcal.theMovie.presentation.viewmodel.MoviesViewModel
import io.jcal.theMovie.utils.SpacingItemDecoration
import io.jcal.theMovie.utils.toTransitionGroup
import javax.inject.Inject

class PopularMoviesFragment : DaggerFragment() {

    private lateinit var binding: FragmentPopularMoviesListBinding
    private lateinit var adapter: MovieAdapter
    private var recyclerInstanceState: MovieUIModelList? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<MoviesViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_popular_movies_list,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter(
            movieClickListener = { movie, _, poster ->
                val extras = FragmentNavigatorExtras(
                    poster.toTransitionGroup()
                )
                findNavController()
                    .navigate(PopularMoviesFragmentDirections.popularMoviesToMovieDetail(movie.id,movie.posterPath),extras)
            }
        )
        binding.popularMoviesRv.adapter = adapter
        binding.popularMoviesRv.addItemDecoration(
            SpacingItemDecoration(
                requireContext(),
                SPACING
            )
        )
        binding.popularMoviesRv.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            recyclerInstanceState = savedInstanceState.getParcelable(
                BUNDLE_MOVIES_INSTANCE_STATE
            )
            recyclerInstanceState?.let {
                viewModel.popularMovies(it)
            }
        }

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when (response.state) {
                SUCCESS -> {
                    if (recyclerInstanceState == null) {
                        adapter.addAll(response.results)
                    }
                    recyclerInstanceState?.let {
                        binding.popularMoviesRv.layoutManager?.onRestoreInstanceState(it)
                        recyclerInstanceState = null
                    }
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.popularMovies.value?.let {
            binding.popularMoviesRv.layoutManager?.let { manager ->
                outState.putParcelable(
                    BUNDLE_MOVIES_INSTANCE_STATE,
                    manager.onSaveInstanceState()
                )
            }
        }
    }

    companion object {
        private const val BUNDLE_MOVIES_INSTANCE_STATE = "movies_instance_state"
        private const val SPACING = 16
    }
}
