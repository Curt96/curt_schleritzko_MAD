package com.example.learningDiary.viewModels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.learningDiary.models.Genre
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies

class MovieViewModel:ViewModel() {
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
    data class ListItemSelectable(
        val title: Genre,
        val isSelected: Boolean
    )
    fun isValidMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, rating: Float): Boolean {
        return (title.isNotBlank()
                && year.isNotBlank()
                && genres.isNotEmpty()
                && director.isNotBlank()
                && actors.isNotBlank()
                && rating > 0.0f)
    }

    fun addNewMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, plot: String, images: List<String>, rating: Float) {
        if(isValidMovie(title, year, genres, director, actors, rating)) {
            val lastId = _movieList.last().id.substring(2).toIntOrNull() ?: 0 //skip 'tt' in the beginning
            _movieList.add(
                Movie(
                    id = "tt${lastId + 1}",
                    title = title,
                    year = year,
                    genre = genres.toString(),
                    director = director,
                    actors = actors,
                    plot = plot,
                    images = images,
                    rating = rating
                )
            )
        }
    }
}