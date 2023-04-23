package com.example.learningDiary.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.Widgets.MovieRow
import com.example.learningDiary.Widgets.SimpleAppBar
import com.example.learningDiary.viewModels.FavoriteScreenViewModel
import com.example.learningDiary.viewModels.HomeScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavHostController, homeScreenViewModel: HomeScreenViewModel, favoriteScreenViewModel: FavoriteScreenViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val favoriteState by favoriteScreenViewModel.getAllFavorites()
        .collectAsState(initial = emptyList())

    Column {
        SimpleAppBar("Favorites", navController = navController)
        Text(
            modifier = Modifier,
            text = "Favorites",
            fontSize = MaterialTheme.typography.h2.fontSize
        )
        if (favoriteState.isEmpty()) {
            Text(
                modifier = Modifier.padding(15.dp),
                style = MaterialTheme.typography.h5,
                text = "No favorite Movies listed!"
            )
        } else {
            for (movieEntity: MovieEntity in favoriteState) {
                MovieRow(
                    //homeScreenViewModel = homeScreenViewModel,
                    movie = movieEntity,
                    favorite = movieEntity.isFavorite,
                    onFavoriteChange = {
                        coroutineScope.launch {
                            favoriteScreenViewModel.updateFavorites(movieEntity)
                        }
                    })
            }
        }
    }
}