package com.example.learningDiary.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learningDiary.Widgets.MovieRow
import com.example.learningDiary.Widgets.SimpleAppBar
import com.example.learningDiary.models.getMovies

@Composable
fun FavoriteScreen(navController: NavHostController) {
    Column {
        SimpleAppBar("Favorites", navController = navController)
        Text(modifier = Modifier,
            text = "Favorites",
            fontSize = MaterialTheme.typography.h2.fontSize)
    }
    MyList()
}