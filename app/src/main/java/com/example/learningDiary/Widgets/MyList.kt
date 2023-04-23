package com.example.learningDiary.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.Widgets.MovieRow
import com.example.learningDiary.viewModels.FavoriteScreenViewModel
import com.example.learningDiary.viewModels.HomeScreenViewModel
import com.example.learningDiary.viewModels.MovieViewModel
import kotlinx.coroutines.launch

@Composable
fun MyList(navController: NavController = rememberNavController(),
           favoriteScreenViewModel: FavoriteScreenViewModel,
           homeScreenViewModel: HomeScreenViewModel,)
           {
               val coroutineScope = rememberCoroutineScope()
               val movies by homeScreenViewModel.movies.collectAsState()
               val moviesState = rememberUpdatedState(movies)

    LazyColumn {
        items(moviesState.value) { movie ->
            MovieRow(
                movie = movie,
                favorite = movie.isFavorite,
                onFavoriteChange = { coroutineScope.launch {
                    favoriteScreenViewModel.updateFavorites(movie)
                }}
            ) { movieId ->
                //Log.d("MyList", "item clicked $movieId")
                // navigate to detailscreen
                navController.navigate("detail/$movieId")
            }
        }
    }
}