package com.example.learningDiary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningDiary.DataRoom.Entities.Movie
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.DataRoom.Repositories.MovieRepository
import com.example.learningDiary.models.Genre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddMovieScreenViewModel(private val movieRepository: MovieRepository): ViewModel() {

    init {
        viewModelScope.launch {  }
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

    suspend fun addMovie(movieEntity: MovieEntity) {
        movieRepository.add(movieEntity)
    }
    /*fun addNewMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, plot: String, images: List<String>, rating: Float) {
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
    }*/
}