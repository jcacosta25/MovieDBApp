package io.jcal.theMovie.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.jcal.theMovie.R
import io.jcal.theMovie.presentation.ui.adapter.MovieAdapter
import io.jcal.theMovie.presentation.ui.home.viewmodel.MoviesViewModel
import io.jcal.theMovie.utils.SpacingItemDecoration
import io.jcal.theMovie.utils.toTransitionGroup

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
	
	private lateinit var recyclerView: RecyclerView
	private lateinit var adapter: MovieAdapter
	
	private val viewModel by viewModels<MoviesViewModel>()
	
	override fun onCreateView(
	    inflater: LayoutInflater,
	    container: ViewGroup?,
	    savedInstanceState: Bundle?
	): View {
		val view = inflater.inflate(R.layout.fragment_popular_movies_list, container, false)
		recyclerView = view.findViewById(R.id.popular_movies_rv)
		return view
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
		recyclerView.adapter = adapter
		recyclerView.addItemDecoration(
			SpacingItemDecoration(
				requireContext(),
				SPACING
			)
		)
		recyclerView.setHasFixedSize(true)
		
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
