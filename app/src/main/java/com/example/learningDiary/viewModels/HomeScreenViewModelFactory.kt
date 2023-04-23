package com.example.learningDiary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningDiary.DataRoom.Repositories.MovieRepository

class HomeScreenViewModelFactory(private val movieRepository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)){
            return HomeScreenViewModel(movieRepository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")

    }
}