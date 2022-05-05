package ru.zamilov.nytimesmovie.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.zamilov.nytimesmovie.model.Movie

class MoviesAdapter : PagingDataAdapter<Movie, MoviesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder.create(parent)

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
        holder.bind(getItem(position))

    object DiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.display_title == newItem.display_title

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}