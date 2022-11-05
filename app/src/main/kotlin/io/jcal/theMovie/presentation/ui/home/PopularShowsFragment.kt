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
import io.jcal.theMovie.presentation.ui.adapter.ShowsAdapter
import io.jcal.theMovie.presentation.ui.home.viewmodel.TvShowsViewModel
import io.jcal.theMovie.utils.SpacingItemDecoration
import io.jcal.theMovie.utils.toTransitionGroup

@AndroidEntryPoint
class PopularShowsFragment : Fragment() {
	
	private val viewModel by viewModels<TvShowsViewModel>()
	private lateinit var recyclerView: RecyclerView
	private lateinit var adapter: ShowsAdapter
	
	override fun onCreateView(
	    inflater: LayoutInflater,
	    container: ViewGroup?,
	    savedInstanceState: Bundle?
	): View {
		val view = inflater.inflate(R.layout.fragment_popular_shows, container, false)
		recyclerView = view.findViewById(R.id.popular_shows_rv)
		return view
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
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
						),
						extras
					)
			}
		)
		viewModel.tvShows.observe(
			requireActivity()
		) {
			adapter.submitList(it)
		}
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		recyclerView.adapter = adapter
		recyclerView.addItemDecoration(
			SpacingItemDecoration(
				requireContext(),
				SPACING
			)
		)
		recyclerView.setHasFixedSize(true)
	}
	
	companion object {
		private const val SPACING = 16
	}
}
