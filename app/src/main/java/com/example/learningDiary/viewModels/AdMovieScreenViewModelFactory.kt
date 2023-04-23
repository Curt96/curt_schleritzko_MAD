package com.example.learningDiary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningDiary.DataRoom.Repositories.MovieRepository

class AddMovieScreenViewModelFactory(private val movierepository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddMovieScreenViewModel::class.java)){
            return AddMovieScreenViewModel(movierepository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown Viewmodel class")

    }
}