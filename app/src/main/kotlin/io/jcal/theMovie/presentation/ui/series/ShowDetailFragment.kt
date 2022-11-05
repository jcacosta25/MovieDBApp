package io.jcal.theMovie.presentation.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import io.jcal.theMovie.R
import io.jcal.theMovie.presentation.mapper.model.BaseUIModel.Companion.SUCCESS
import io.jcal.theMovie.utils.DataBindingAdapter

@AndroidEntryPoint
class ShowDetailFragment : Fragment() {
	
	private val viewModel by viewModels<TvShowDetailViewModel>()
	private lateinit var backdrop: AppCompatImageView
	private lateinit var poster: AppCompatImageView
	private lateinit var title: AppCompatTextView
	private lateinit var date: AppCompatTextView
	private lateinit var overView: AppCompatTextView
	
	private val args: ShowDetailFragmentArgs by navArgs()
	
	private val tvShowId by lazy { args.showId }
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		sharedElementEnterTransition =
			TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
	}
	
	override fun onCreateView(
	    inflater: LayoutInflater,
	    container: ViewGroup?,
	    savedInstanceState: Bundle?
	): View {
		val view = inflater.inflate(R.layout.fragment_show_detail, container, false)
		backdrop = view.findViewById(R.id.show_backdrop_image_view)
		poster = view.findViewById(R.id.show_poster_image_view)
		title = view.findViewById(R.id.show_title_text_view)
		date = view.findViewById(R.id.show_date_text_view)
		overView = view.findViewById(R.id.show_overview_text_view)
		return view
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		poster.apply {
			transitionName = args.uri
			Picasso.get()
				.load(args.uri)
				.placeholder(R.drawable.background_place_holder)
				.error(R.drawable.background_place_holder)
				.fit()
				.centerCrop()
				.into(this)
		}
		
		viewModel.getTvShow(tvShowId)
		
		viewModel.tvShowDetail().observe(
			viewLifecycleOwner
		) { show ->
			when (show.state) {
				SUCCESS -> {
					DataBindingAdapter.loadImage(backdrop, show.backdropPath)
					DataBindingAdapter.loadImage(poster, show.posterPath)
					title.text = show.name
					date.text = show.firstAirDate
					overView.text = show.overview
				}
			}
		}
	}
}
