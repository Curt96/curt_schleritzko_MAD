package com.example.learningDiary.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies
import java.lang.reflect.Modifier

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {

    movieId?.let {
        val selectedMovie = getMovies().find { element: Movie -> element.id == movieId }

    Column {
        if (selectedMovie != null) {
            AppBar(selectedMovie.title,)
            MovieRow(movie = selectedMovie)
        }
        Divider(startIndent = 5.dp, thickness = 1.dp, color = Color.DarkGray)
        Text(text = "Movie Images", fontSize = MaterialTheme.typography.h2.fontSize, )

    }
    }



}