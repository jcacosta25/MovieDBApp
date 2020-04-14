package io.jcal.theMovie.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.MoviePreviewContentBinding
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel

class MovieAdapter(
    private val movies: MutableList<MovieUIModel> = mutableListOf(),
    val movieClickListener: (MovieUIModel, Int, View) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

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
        holder.bind(movieClickListener, position, movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(private val binding: MoviePreviewContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movieClickListener: (MovieUIModel, Int, View) -> Unit,
            position: Int,
            movie: MovieUIModel
        ) {
            binding.movie = movie
            binding.root.setOnClickListener {
                movieClickListener.invoke(movie, position, binding.appCompatImageView)
            }

            binding.executePendingBindings()
        }
    }
}
