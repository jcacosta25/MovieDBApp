package io.jcal.theMovie.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.jcal.theMovie.R
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import io.jcal.theMovie.utils.DataBindingAdapter

class ShowsAdapter(
    val showClickListener: (TvShowUIModel, Int, View) -> Unit = { _, _, _ -> }
) : PagedListAdapter<TvShowUIModel, ShowsAdapter.ShowViewHolder>(TvShowUIModel.SHOW_DIF) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder =
		ShowViewHolder(
			LayoutInflater.from(parent.context)
				.inflate(R.layout.show_preview_content, parent, false)
		)
	
	override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
		getItem(position)?.let {
			holder.bind(it, showClickListener, position)
		}
	}
	
	inner class ShowViewHolder(private val view: View) :
		RecyclerView.ViewHolder(view) {
		private val image = view.findViewById<AppCompatImageView>(R.id.show_poster_image_view)
		private val title = view.findViewById<TextView>(R.id.show_title_text)
		private val date = view.findViewById<TextView>(R.id.show_date_text)
		private val overview = view.findViewById<TextView>(R.id.show_overview_text)
		
		fun bind(
		    show: TvShowUIModel,
		    showClickListener: (TvShowUIModel, Int, View) -> Unit,
		    position: Int
		) {
			DataBindingAdapter.loadImage(image, show.posterPath)
			view.setOnClickListener {
				showClickListener.invoke(show, position, image)
			}
			title.text = show.name
			date.text = show.firstAirDate
			overview.text = show.overview
			view.setOnClickListener {
				showClickListener.invoke(
					show,
					position,
					image
				)
			}
		}
	}
}
