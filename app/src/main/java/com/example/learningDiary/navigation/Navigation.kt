package com.example.learningDiary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learningDiary.screens.AddMovieScreen
import com.example.learningDiary.screens.DetailScreen
import com.example.learningDiary.screens.FavoriteScreen
import com.example.learningDiary.screens.HomeScreen
import com.example.learningDiary.utils.InjectorUtils
import com.example.learningDiary.viewModels.*

@Composable
fun Navigation(
    moviesViewModel: MovieViewModel,
    favoriteScreenViewModel: FavoriteScreenViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    addMovieScreenViewModel: AddMovieScreenViewModel)
    {
    val navController = rememberNavController()
    var detailScreenViewModel: DetailScreenViewModel

    NavHost(navController = navController, startDestination = "home", ) {
        composable(route = "home") {
            HomeScreen(navController, homeScreenViewModel, favoriteScreenViewModel)
            //detailScreenViewModel = viewModel(factory = InjectorUtils.provideDetailScreenViewModelFactory(LocalContext.current))
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ) {backStackEntry ->
            backStackEntry.arguments?.getInt("movieId") ?.let {
                DetailScreen(
                    homeScreenViewModel = homeScreenViewModel,
                    navController = navController,
                    favoriteScreenViewModel = favoriteScreenViewModel,
                    movieId = it
                )
            }
        }
        composable(route = "favorites"){
            FavoriteScreen(navController, homeScreenViewModel,favoriteScreenViewModel)
        }
        composable(route = "addMovie") {
            AddMovieScreen(Modifier, addMovieScreenViewModel, navController)
        }
    }
}