package com.example.learningDiary.DataRoom.Repositories

import com.example.learningDiary.DataRoom.DAOs.MovieDAO
import com.example.learningDiary.DataRoom.Entities.Movie
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDAO: MovieDAO) {

    suspend fun add(movieEntity: MovieEntity) = movieDAO.addMovie(movieEntity)

    suspend fun delete(movieEntity: MovieEntity) = movieDAO.deleteMovie(movieEntity)

    suspend fun update(movieEntity: MovieEntity) = movieDAO.updateMovie(movieEntity)

    fun getAllMovies() : Flow<List<MovieEntity>> = movieDAO.getAllMovies()

    fun getMovieById(id: Int) : Flow<MovieEntity> = movieDAO.getMovieById(id)

    fun getAllFavorites() : Flow<List<MovieEntity>> = movieDAO.getAllFavorites()

}