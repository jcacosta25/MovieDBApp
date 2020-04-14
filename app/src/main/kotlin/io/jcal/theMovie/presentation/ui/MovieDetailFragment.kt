package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.FragmentMovieDetailBinding
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.presentation.viewmodel.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MovieDetailViewModel> { viewModelFactory }
    private lateinit var binding: FragmentMovieDetailBinding

    private val args: MovieDetailFragmentArgs by navArgs()

    private val movieId by lazy { args.movieId }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.showPosterImageView.apply {
            transitionName = args.uri
            Picasso.get()
                .load(args.uri)
                .placeholder(R.drawable.background_place_holder)
                .error(R.drawable.background_place_holder)
                .fit()
                .centerCrop()
                .into(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getMovie(movieId)

        viewModel.movieDetail().observe(viewLifecycleOwner, Observer { movie ->
            when (movie.state) {
                SUCCESS -> {
                    binding.movie = movie
                }
            }
        })
    }
}
