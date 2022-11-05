package io.jcal.theMovie.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.jcal.theMovie.R
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import io.jcal.theMovie.utils.DataBindingAdapter

class MovieAdapter(private val movieClickListener: (MovieUIModel, Int, View) -> Unit) :
	PagedListAdapter<MovieUIModel, MovieAdapter.MovieViewHolder>(MovieUIModel.MOVIE_DIF) {
	
	override fun onCreateViewHolder(
	    parent: ViewGroup,
	    viewType: Int
	): MovieViewHolder = MovieViewHolder(
		LayoutInflater.from(parent.context)
		.inflate(R.layout.movie_preview_content, parent, false)
	)
	
	override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
		getItem(position)?.let {
			holder.bind(movieClickListener, position, it)
		}
	}
	
	inner class MovieViewHolder(private val view: View) :
		RecyclerView.ViewHolder(view) {
		
		private val image = view.findViewById<AppCompatImageView>(R.id.movie_poster_image_view)
		private val title = view.findViewById<TextView>(R.id.movie_title_text)
		private val rating = view.findViewById<TextView>(R.id.movie_rating_text_view)
		private val date = view.findViewById<TextView>(R.id.movie_date_text)
		private val overview = view.findViewById<TextView>(R.id.movie_overview_text)
		fun bind(
		    movieClickListener: (MovieUIModel, Int, View) -> Unit,
		    position: Int,
		    movie: MovieUIModel
		) {
			DataBindingAdapter.loadImage(image, movie.posterPath)
			view.setOnClickListener {
				movieClickListener.invoke(movie, position, image)
			}
			title.text = movie.title
			rating.text = movie.voteAverage.toString()
			date.text = movie.releaseDate
			overview.text = movie.overview
		}
	}
}
