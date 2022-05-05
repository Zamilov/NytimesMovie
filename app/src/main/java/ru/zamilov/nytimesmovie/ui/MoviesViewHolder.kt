package ru.zamilov.nytimesmovie.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.zamilov.nytimesmovie.R
import ru.zamilov.nytimesmovie.databinding.MovieCardBinding
import ru.zamilov.nytimesmovie.model.Movie

class MoviesViewHolder(private val binding: MovieCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var movie: Movie? = null

    init {
        binding.root.setOnClickListener {
            movie?.link?.url.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                binding.root.context.startActivity(intent)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_card, parent, false)
            return MoviesViewHolder(MovieCardBinding.bind(view))
        }
    }

    fun bind(movie: Movie?) {
        this.movie = movie

        if (movie != null) {
            binding.movieTitle.text = movie.display_title
            binding.movieDescription.text = movie.summary_short
            val imgUrl = movie.multimedia.src.toUri()
                .buildUpon()
                .scheme("https")
                .build()
            Glide.with(binding.moviePicture.context)
                .load(imgUrl)
                .apply(RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_img)
                )
                .into(binding.moviePicture)
        }
    }
}