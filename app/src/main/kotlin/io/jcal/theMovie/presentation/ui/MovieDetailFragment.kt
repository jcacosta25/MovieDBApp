package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentMovieDetailBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.presentation.ui.MovieDetailFragmentArgs.fromBundle
import io.jcal.theMovie.presentation.viewmodel.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: FragmentMovieDetailBinding

    private val movieId by lazy { fromBundle(arguments!!).movieId }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        viewModel.getMovie(movieId)

        viewModel.movieDetail().observe(this, Observer { movie ->
            when (movie.state) {
                SUCCESS -> {
                    binding.movie = movie
                }
            }
        })
    }
}
