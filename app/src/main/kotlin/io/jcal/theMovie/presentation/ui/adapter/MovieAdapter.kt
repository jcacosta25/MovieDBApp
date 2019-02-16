package io.jcal.theMovie.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.MoviePreviewContentBinding
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel

class MovieAdapter(private val movies: MutableList<MovieUIModel> = mutableListOf()) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var listener: ItemAdapterClickLIstener<MovieUIModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_preview_content,
                parent,
                false
            )
        )

    fun addAll(movies: List<MovieUIModel>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listener, position, movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun setClickListener(listener: ItemAdapterClickLIstener<MovieUIModel>) {
        this.listener = listener
    }

    class MovieViewHolder(private val binding: MoviePreviewContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listener: ItemAdapterClickLIstener<MovieUIModel>,
            position: Int,
            movie: MovieUIModel
        ) {
            binding.movie = movie
            binding.root.setOnClickListener {
                listener.onItemClick(it, position, movie)
            }

            binding.executePendingBindings()
        }

    }
}