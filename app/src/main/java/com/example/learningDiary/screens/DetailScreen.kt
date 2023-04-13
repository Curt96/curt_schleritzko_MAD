package com.example.learningDiary.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.learningDiary.Widgets.HorizontalImageView
import com.example.learningDiary.Widgets.MovieRow
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies
import com.example.learningDiary.viewModels.MovieViewModel

@Composable
fun DetailScreen(navController: NavController,moviesViewModel: MovieViewModel, movieId: String?) {

    movieId?.let {
        val selectedMovie = movieId?.let { moviesViewModel.findMovieById(movieId) }

    Column {
        if (selectedMovie != null) {
            com.example.learningDiary.Widgets.SimpleAppBar(selectedMovie.title, navController)
            MovieRow(
                movie = selectedMovie,
                favorite = selectedMovie.isFavorite,
                onFavoriteChange = { favorite -> moviesViewModel.changeFavState(selectedMovie, favorite) }
            )
        }
        Divider(startIndent = 5.dp, thickness = 1.dp, color = Color.DarkGray)
        Text(text = "Movie Images", fontSize = MaterialTheme.typography.h2.fontSize, )
        if (selectedMovie != null) {
            HorizontalImageView(movie = selectedMovie)
        }
    }
    }
}