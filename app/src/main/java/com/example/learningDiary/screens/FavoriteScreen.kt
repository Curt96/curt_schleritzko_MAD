package com.example.learningDiary.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learningDiary.Widgets.MovieRow
import com.example.learningDiary.Widgets.SimpleAppBar
import com.example.learningDiary.viewModels.MovieViewModel

@Composable
fun FavoriteScreen(navController: NavHostController, movieViewModel: MovieViewModel) {
    Column {
        SimpleAppBar("Favorites", navController = navController)
        Text(
            modifier = Modifier,
            text = "Favorites",
            fontSize = MaterialTheme.typography.h2.fontSize
        )
        if (movieViewModel.getFavorites().isEmpty()) {
            Text(
                modifier = Modifier.padding(15.dp),
                style = MaterialTheme.typography.h5,
                text = "No favorite Movies listed!"
            )
        } else {
            MyList(
                navController,
                movieList = movieViewModel.getFavorites(),
                onFavoriteMovie = { movie, favorite ->
                    movieViewModel.changeFavState(movie, favorite)
                })
        }
    }
}