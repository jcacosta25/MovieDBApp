package io.jcal.theMovie.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.ShowPreviewContentBinding
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel

class ShowAdapter(
    private val movies: MutableList<TvShowUIModel> = mutableListOf(),
    val showClickListener: (TvShowUIModel, Int, View) -> Unit
) :
    RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder =
        ShowViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.show_preview_content,
                parent,
                false
            )
        )

    fun addAll(show: List<TvShowUIModel>) {
        this.movies.addAll(show)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(movies[position], showClickListener, position)
    }

    override fun getItemCount(): Int = movies.size

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
