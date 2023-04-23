package com.example.learningDiary.viewModels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.learningDiary.DataRoom.Entities.Movie
import com.example.learningDiary.DataRoom.Entities.getMovies
import com.example.learningDiary.DataRoom.Repositories.MovieRepository
import com.example.learningDiary.models.Genre



class MovieViewModel(repository: MovieRepository) :ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    fun changeFavState(movie: Movie, favorite: Boolean) {
        movieList.find { it == movie }?.let { foundMovie ->
            foundMovie.isFavorite = favorite
        }
    }
    fun getFavorites(): List<Movie> {
       return movieList.filter { movie: Movie -> movie.isFavorite }
    }
    fun findMovieById(movieId: String): Movie? {
        return movieList.find { it.id == movieId }
    }

}