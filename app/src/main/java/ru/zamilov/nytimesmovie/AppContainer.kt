package ru.zamilov.nytimesmovie

import ru.zamilov.nytimesmovie.api.NytimesApiService
import ru.zamilov.nytimesmovie.data.NytimesRepository

class AppContainer {
    private val apiService = NytimesApiService.create()
    val repository = NytimesRepository(apiService)
}