package com.example.learningDiary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.DataRoom.Entities.getMovies

import com.example.learningDiary.DataRoom.Repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

class HomeScreenViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private val _movieList = MutableStateFlow(listOf<MovieEntity>())
    val movies: StateFlow<List<MovieEntity>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch { //in Threads/ CoroutineScope legen (nicht in UI thread
            movieRepository.getAllMovies().collect(){collectedMovies ->
                if (!collectedMovies.isNullOrEmpty()) {
                    _movieList.value = collectedMovies
                }
                //for (movie in getMovies()) {
                //    movieRepository.add(movie)
                //}
            }
        }
    }

    suspend fun changeFavstate(movieEntity: MovieEntity) {
        movieEntity.isFavorite = !movieEntity.isFavorite
        movieRepository.update(movieEntity)
    }
    suspend fun deleteMovie(movieEntity: MovieEntity) {
        movieRepository.delete(movieEntity)
    }





}