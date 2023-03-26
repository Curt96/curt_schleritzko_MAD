package com.example.learningDiary.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.learningDiary.models.getMovies

@Composable
fun FavoriteScreen(navController: NavHostController) {
    Column {
        AppBar("Favorites", navController = navController)
        Text(modifier = Modifier,
            text = "Favorites",
            fontSize = MaterialTheme.typography.h2.fontSize)
        MovieRow(movie = getMovies()[2])
        Spacer(modifier = Modifier.size(3.dp))
        MovieRow(movie = getMovies()[4])
    }
}