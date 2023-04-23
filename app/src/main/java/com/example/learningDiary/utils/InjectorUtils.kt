package com.example.learningDiary.utils

import android.content.Context
import com.example.learningDiary.DataRoom.Database.MovieDatabase
import com.example.learningDiary.DataRoom.Repositories.MovieRepository
import com.example.learningDiary.viewModels.*

object InjectorUtils {

    private fun getMovieRepository(context: Context): MovieRepository{
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory {
        val repository = getMovieRepository(context)
        return MovieViewModelFactory(repository)
    }
    fun provideFavoriteScreenViewModelFactory(context: Context): FavoriteScreenViewModelFactory {
        return FavoriteScreenViewModelFactory(getMovieRepository(context))
    }
    fun provideHomeScreenViewModelFactory(context: Context): HomeScreenViewModelFactory {
        return HomeScreenViewModelFactory(getMovieRepository(context))
    }
    fun provideAddMovieScreenViewModelFactory(context: Context): AddMovieScreenViewModelFactory{
        return AddMovieScreenViewModelFactory(getMovieRepository(context))
    }
}