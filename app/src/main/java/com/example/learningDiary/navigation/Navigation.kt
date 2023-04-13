package com.example.learningDiary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learningDiary.screens.DetailScreen
import com.example.learningDiary.screens.FavoriteScreen
import com.example.learningDiary.screens.HomeScreen
import com.example.learningDiary.viewModels.MovieViewModel

@Composable
fun Navigation(moviesViewModel: MovieViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController)
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ) {backStackEntry ->
            DetailScreen(navController, moviesViewModel, movieId = backStackEntry.arguments?.getString("movieId"))
        }
        composable(route = "favorites"){
            FavoriteScreen(navController, moviesViewModel)
        }
    }
}