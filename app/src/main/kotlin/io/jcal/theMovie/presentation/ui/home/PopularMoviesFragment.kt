package io.jcal.theMovie.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.jcal.theMovie.presentation.ui.home.viewmodel.MoviesViewModel

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
	
	private val viewModel by viewModels<MoviesViewModel>()
	
	override fun onCreateView(
	    inflater: LayoutInflater,
	    container: ViewGroup?,
	    savedInstanceState: Bundle?
	): View = ComposeView(requireContext()).apply {
		setContent {
			PopularMoviesList(
				viewModel = viewModel,
				navigateToMovie = { movie ->
					findNavController().navigate(
						PopularMoviesFragmentDirections.popularMoviesToMovieDetail(
							movie.id,
							movie.posterPath
						)
					)
				}
			)
		}
	}
}
