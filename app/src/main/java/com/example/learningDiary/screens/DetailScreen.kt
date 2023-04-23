package com.example.learningDiary.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.learningDiary.DataRoom.Database.MovieDatabase
import com.example.learningDiary.DataRoom.Repositories.MovieRepository
import com.example.learningDiary.Widgets.HorizontalImageView
import com.example.learningDiary.Widgets.MovieRow
import com.example.learningDiary.viewModels.*

import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController, movieId: Int, favoriteScreenViewModel: FavoriteScreenViewModel,homeScreenViewModel: HomeScreenViewModel) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDAO = db.movieDao())
    val factory = DetailScreenViewModelFactory(movieRepository = repository, movieId = movieId)
    val detailScreenViewModel: DetailScreenViewModel = viewModel(factory = factory)
    val movie = detailScreenViewModel.movie.value
    var title = ""
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    //movieId?.let {
    //  val selectedMovie = movieId?.let { moviesViewModel.findMovieById(movieId) }

    if (movie == null) {
        Text(text = "No Movie found")
    } else {
        Column {

                title = movie.title
                com.example.learningDiary.Widgets.SimpleAppBar(title, navController)
                MovieRow(
                    movie = movie,
                    favorite = movie.isFavorite,
                    onFavoriteChange = {
                        coroutineScope.launch {
                            favoriteScreenViewModel.changeFavstate(movie)
                        }
                    }
                )
            Divider(startIndent = 5.dp, thickness = 1.dp, color = Color.DarkGray)
            Text(text = "Movie Images", fontSize = MaterialTheme.typography.h2.fontSize,)
            HorizontalImageView(movieEntity = movie)
            }

        }

}
