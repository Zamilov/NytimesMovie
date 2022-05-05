package ru.zamilov.nytimesmovie.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.zamilov.nytimesmovie.api.NytimesApiService
import ru.zamilov.nytimesmovie.model.Movie
import java.io.IOException

class NytimesPagingSource(private val apiService: NytimesApiService) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = apiService.getMovies(nextPageNumber)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber.plus(20)
            )
        } catch (exp: IOException) {
            LoadResult.Error(exp)
        } catch (exp: HttpException) {
            LoadResult.Error(exp)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(20) ?: anchorPage?.nextKey?.minus(20)
        }
    }
}