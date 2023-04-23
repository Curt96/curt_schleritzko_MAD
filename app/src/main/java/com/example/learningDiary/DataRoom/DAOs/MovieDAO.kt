package com.example.learningDiary.DataRoom.DAOs

import androidx.room.*
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDAO {
    //CRUD Create, Upgrade Delete

    @Insert
    suspend fun addMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("Select * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("Select * from movie where isFavorite = true")
    fun getAllFavorites(): Flow<List<MovieEntity>>

    @Query("Select * from movie where id =:movieId")
    fun getMovieById(movieId: Int): Flow<MovieEntity>
}