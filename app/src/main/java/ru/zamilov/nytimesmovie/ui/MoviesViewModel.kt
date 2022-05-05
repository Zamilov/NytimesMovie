package ru.zamilov.nytimesmovie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.zamilov.nytimesmovie.data.NytimesRepository
import ru.zamilov.nytimesmovie.model.Movie

class MoviesViewModel(repository: NytimesRepository) : ViewModel() {
    val flow: Flow<PagingData<Movie>> = repository.getMovies().cachedIn(viewModelScope)
}