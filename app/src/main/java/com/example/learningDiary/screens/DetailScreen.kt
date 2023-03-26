package com.example.learningDiary.screens

import android.provider.MediaStore.Images
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
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies
import java.lang.reflect.Modifier

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {

    movieId?.let {
        val selectedMovie = getMovies().find { element: Movie -> element.id == movieId }

    Column {
        if (selectedMovie != null) {
            AppBar(selectedMovie.title, navController)
            MovieRow(movie = selectedMovie)
        }
        Divider(startIndent = 5.dp, thickness = 1.dp, color = Color.DarkGray)
        Text(text = "Movie Images", fontSize = MaterialTheme.typography.h2.fontSize, )
        if (selectedMovie != null) {
            movieImage(images = selectedMovie.images )
        }
    }
    }
}

@Composable
fun movieImage(images: List<String>) {
    LazyRow(contentPadding = PaddingValues(all = 5.dp)) {
        items(images) {
            image -> Card() {
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = "Movie Images",
                contentScale = ContentScale.Crop,
            )
        }
        }
    }
}
