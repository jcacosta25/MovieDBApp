package io.jcal.theMovie.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import io.jcal.theMovie.databinding.FragmentPopularMoviesListBinding
import io.jcal.theMovie.presentation.ui.adapter.MovieAdapter
import io.jcal.theMovie.presentation.ui.home.viewmodel.MoviesViewModel
import io.jcal.theMovie.utils.SpacingItemDecoration
import io.jcal.theMovie.utils.toTransitionGroup
import javax.inject.Inject

class PopularMoviesFragment : DaggerFragment() {
	
	private lateinit var binding: FragmentPopularMoviesListBinding
	private lateinit var adapter: MovieAdapter
	
	@Inject
	lateinit var factory: ViewModelProvider.Factory
	
	private val viewModel by viewModels<MoviesViewModel> { factory }
	
	override fun onCreateView(
	    inflater: LayoutInflater,
	    container: ViewGroup?,
	    savedInstanceState: Bundle?
	): View = FragmentPopularMoviesListBinding.inflate(
		inflater,
		container,
		false
	).let {
		binding = it
		binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		adapter = MovieAdapter(
			movieClickListener = { movie, _, poster ->
				val extras = FragmentNavigatorExtras(
					poster.toTransitionGroup()
				)
				findNavController()
					.navigate(
						PopularMoviesFragmentDirections.popularMoviesToMovieDetail(
							movie.id,
							movie.posterPath
						),
						extras
					)
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
		
		viewModel.moviesLiveData.observe(
			viewLifecycleOwner
		) {
			adapter.submitList(it)
		}
	}
	
	companion object {
		private const val SPACING = 16
	}
}
