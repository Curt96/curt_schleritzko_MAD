package com.example.learningDiary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.DataRoom.Repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

class FavoriteScreenViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _favoritesMovie = MutableStateFlow(listOf<MovieEntity>())
    val movies: StateFlow<List<MovieEntity>> = _favoritesMovie.asStateFlow()

    init {
        viewModelScope.launch { //in Threads/ CoroutineScope legen (nicht in UI thread
            movieRepository.getAllMovies().collect() { collectedMovies ->
                if (!collectedMovies.isNullOrEmpty()) {
                    _favoritesMovie.value = collectedMovies
                }
            }
        }
    }

    suspend fun changeFavstate(movieEntity: MovieEntity) {
        movieEntity.isFavorite = !movieEntity.isFavorite
        movieRepository.update(movieEntity)
    }
    fun getAllFavorites(): Flow<List<MovieEntity>> {
        return movieRepository.getAllFavorites()
    }

    suspend fun updateFavorites(movieEntity: MovieEntity) {
        movieEntity.isFavorite = !movieEntity.isFavorite
        movieRepository.update(movieEntity)
    }
}