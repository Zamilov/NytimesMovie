package ru.zamilov.nytimesmovie.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.zamilov.nytimesmovie.api.NytimesApiService
import ru.zamilov.nytimesmovie.model.Movie

class NytimesRepository(private val apiService: NytimesApiService) {

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { NytimesPagingSource(apiService) }
        ).flow
    }
}