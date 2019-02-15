package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentPopularMoviesBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.presentation.ui.adapter.MovieAdapter
import io.jcal.theMovie.presentation.viewmodel.MoviesViewModel
import io.jcal.theMovie.utils.SpacingItemDecoration
import javax.inject.Inject

class PopularMoviesFragment : DaggerFragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var binding: FragmentPopularMoviesBinding
    private val adapter: MovieAdapter = MovieAdapter()
    private var recyclerInstanceState: Parcelable? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_popular_movies, container, false)
        binding.popularMoviesRv.adapter = adapter
        binding.popularMoviesRv.addItemDecoration(SpacingItemDecoration(requireContext(), 16))
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)

        binding.popularMoviesRv.setHasFixedSize(true)
        viewModel.popularMovies().observe(this, Observer { response ->
            when (response.state) {
                SUCCESS -> {
                    adapter.addAll(response.results)
                    recyclerInstanceState?.let {
                        binding.popularMoviesRv.layoutManager!!.onRestoreInstanceState(it)
                        recyclerInstanceState = null
                    }
                }
            }
        })
    }
}