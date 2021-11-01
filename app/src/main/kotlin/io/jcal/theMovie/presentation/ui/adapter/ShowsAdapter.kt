package io.jcal.theMovie.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.ShowPreviewContentBinding
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel

class ShowsAdapter(
    val showClickListener: (TvShowUIModel, Int, View) -> Unit = { _, _, _ -> }
) : PagedListAdapter<TvShowUIModel, ShowsAdapter.ShowViewHolder>(TvShowUIModel.SHOW_DIF) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder =
		ShowViewHolder(
			DataBindingUtil.inflate(
				LayoutInflater.from(parent.context),
				R.layout.show_preview_content,
				parent,
				false
			)
		)
	
	override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
		getItem(position)?.let {
			holder.bind(it, showClickListener, position)
		}
	}
	
	inner class ShowViewHolder(private val binding: ShowPreviewContentBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(
		    show: TvShowUIModel,
		    showClickListener: (TvShowUIModel, Int, View) -> Unit,
		    position: Int
		) {
			binding.show = show
			binding.root.setOnClickListener {
				showClickListener.invoke(
					show,
					position,
					binding.showPosterImageView
				)
			}
			binding.executePendingBindings()
		}
	}
}
