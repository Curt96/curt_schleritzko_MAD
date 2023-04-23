package com.example.learningDiary.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningDiary.DataRoom.Repositories.MovieRepository

class FavoriteScreenViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FavoriteScreenViewModel::class.java)){
            return FavoriteScreenViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown Viewmodel class")

    }
}