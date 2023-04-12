package com.example.learningDiary.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.learningDiary.models.Genre
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies

public class MovieViewModel:ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    private val _favoritesList: MutableList<Movie> = mutableStateListOf()
    val favoritesList: List<Movie>
        get() = _favoritesList

    fun findMovieById(movieId: String): Movie? {
        return movieList.find { it.id == movieId }
    }
    fun changeFavState(movie: Movie, favorite: Boolean) {
        movieList.find { it == movie }?.let { foundMovie ->
            foundMovie.isFavorite = favorite
        }
    }
}