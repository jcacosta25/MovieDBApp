package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentPopularMoviesBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.presentation.mapper.model.MovieUIModelList
import io.jcal.theMovie.presentation.ui.PopularMoviesFragmentDirections.popularMoviesToMovieDetail
import io.jcal.theMovie.presentation.ui.adapter.MovieAdapter
import io.jcal.theMovie.presentation.viewmodel.MoviesViewModel
import io.jcal.theMovie.utils.SpacingItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesFragment : DaggerFragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var binding: FragmentPopularMoviesBinding
    private lateinit var adapter: MovieAdapter
    private var recyclerInstanceState: MovieUIModelList? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_movies, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter(
            movieClickListener = { movie, _, rootView ->
                rootView.findNavController().navigate(popularMoviesToMovieDetail(movie.id))
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

        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(MoviesViewModel::class.java)
        if (savedInstanceState != null) {
            recyclerInstanceState = savedInstanceState.getParcelable(
                BUNDLE_MOVIES_INSTANCE_STATE
            )
            recyclerInstanceState?.let {
                viewModel.popularMovies(it)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            val popularMovies = viewModel.popularMoviesCoroutines

            popularMovies.observe(this@PopularMoviesFragment, Observer { response ->
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.popularMovies().value?.let {
            binding.popularMoviesRv.layoutManager?.let { manager ->
                outState.putParcelable(
                    BUNDLE_MOVIES_INSTANCE_STATE,
                    manager.onSaveInstanceState()
                )
            }

        }
    }

    companion object {
        private const val BUNDLE_MOVIE = "movies"
        private const val BUNDLE_MOVIES_INSTANCE_STATE = "movies_instance_state"
        private const val SPACING = 16
    }
}