package com.example.learningDiary.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.DataRoom.Repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val movieRepository: MovieRepository, movieId: Int): ViewModel() {

    private val _movieList: MutableState<MovieEntity?> = mutableStateOf(null)
    val movie: State<MovieEntity?> = _movieList

    init {
        viewModelScope.launch { //in Threads/ CoroutineScope legen (nicht in UI thread
            movieRepository.getMovieById(movieId).collect {movie ->
                _movieList.value = movie
            }
        }
    }
}