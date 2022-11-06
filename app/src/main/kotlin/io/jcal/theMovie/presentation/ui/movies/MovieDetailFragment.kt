package io.jcal.theMovie.presentation.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
	
	private val viewModel by viewModels<MovieDetailViewModel>()
	
	private val args: MovieDetailFragmentArgs by navArgs()
	
	private val movieId by lazy { args.movieId }
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel.getMovie(movieId)
	}
	
	override fun onCreateView(
	    inflater: LayoutInflater,
	    container: ViewGroup?,
	    savedInstanceState: Bundle?
	): View = ComposeView(requireContext()).apply {
		setContent {
			MovieDetail(
				viewModel
			)
		}
	}
}
