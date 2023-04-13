package com.example.learningDiary.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learningDiary.Widgets.MovieRow
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies

@Composable
fun MyList(navController: NavController = rememberNavController(),
           movies: List<Movie> = getMovies(),
           onFavoriteMovie: (Movie, Boolean) -> Unit){
    LazyColumn {
        items(movies) { movie ->
            MovieRow(
                movie = movie,
                favorite = movie.isFavorite,
                onFavoriteChange = { favorite -> onFavoriteMovie(movie, favorite) }
            ) { movieId ->
                Log.d("MyList", "item clicked $movieId")
                // navigate to detailscreen
                navController.navigate("detail/$movieId")
            }
        }
    }
}