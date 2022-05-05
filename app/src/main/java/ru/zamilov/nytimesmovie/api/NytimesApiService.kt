package ru.zamilov.nytimesmovie.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NytimesApiService {
    @GET("/svc/movies/v2/reviews/all.json")
    suspend fun getMovies(
        @Query("offset") offset: Int,
    ): MoviesResponse

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/"

        fun create(): NytimesApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(NytimesInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build())
                    .asLenient()
                )
                .build()
                .create(NytimesApiService::class.java)
        }
    }
}

