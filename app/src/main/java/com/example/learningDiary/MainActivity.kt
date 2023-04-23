package com.example.learningDiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learningDiary.DataRoom.Database.MovieDatabase
import com.example.learningDiary.DataRoom.Repositories.MovieRepository
import com.example.learningDiary.navigation.Navigation
import com.example.learningDiary.ui.theme.LectureExamplesTheme
import com.example.learningDiary.utils.InjectorUtils
import com.example.learningDiary.viewModels.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var favoriteScreenViewModel: FavoriteScreenViewModel
        var detailScreenViewModel: DetailScreenViewModel
        var homeScreenViewModel: HomeScreenViewModel
        var addMovieScreenViewModel: AddMovieScreenViewModel
        setContent {
            LectureExamplesTheme {

                val db = MovieDatabase.getDatabase(LocalContext.current)
                val repository = MovieRepository(movieDAO = db.movieDao())

                val coroutineScope = rememberCoroutineScope()



                favoriteScreenViewModel = viewModel(factory = InjectorUtils.provideFavoriteScreenViewModelFactory(LocalContext.current))
                        //detailScreenViewModel = viewModel(factory = InjectorUtils.provideDetailScreenViewModelFactory(LocalContext.current))
                homeScreenViewModel = viewModel(factory = InjectorUtils.provideHomeScreenViewModelFactory(
                    LocalContext.current))
                addMovieScreenViewModel = viewModel(factory = InjectorUtils.provideAddMovieScreenViewModelFactory(
                    LocalContext.current))


                Column {
                    Navigation(favoriteScreenViewModel = favoriteScreenViewModel,  homeScreenViewModel = homeScreenViewModel, addMovieScreenViewModel = addMovieScreenViewModel)
                }
            }
        }
    }
}


