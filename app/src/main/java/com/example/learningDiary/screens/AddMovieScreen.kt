package com.example.learningDiary.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.R
import com.example.learningDiary.Widgets.SimpleAppBar
import com.example.learningDiary.models.Genre
import com.example.learningDiary.viewModels.AddMovieScreenViewModel
import com.example.learningDiary.viewModels.MovieViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddMovieScreen(modifier: Modifier = Modifier,
                   addMovieScreenViewModel: AddMovieScreenViewModel,
                   navController: NavHostController
) {
    Column {
        SimpleAppBar(title = "Add Movie", navController = navController)
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(15.dp)
        ) {
            val coroutineScope = rememberCoroutineScope()

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                var title by remember {
                    mutableStateOf("")
                }

                var year by remember {
                    mutableStateOf("")
                }

                val genres = Genre.values().toList()

                var genreItems by remember {
                    mutableStateOf(
                        genres.map { genre ->
                            AddMovieScreenViewModel.ListItemSelectable(
                                title = genre,
                                isSelected = false
                            )
                        }
                    )
                }

                var director by remember {
                    mutableStateOf("")
                }

                var actors by remember {
                    mutableStateOf("")
                }

                var plot by remember {
                    mutableStateOf("")
                }

                var rating by remember {
                    mutableStateOf("")
                }

                var isEnabledSaveButton by remember {
                    mutableStateOf(false)
                }
                OutlinedTextField(
                    value = title,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { title = it },
                    label = { Text(text = stringResource(R.string.enter_movie_title)) }
                )
                if (title == ""){
                    Text(
                        text = "Enter Movie Titel",
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = year,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { year = it },
                    label = { Text(stringResource(R.string.enter_movie_year)) }
                )
                if (year == ""){
                    Text(
                        text = "Enter Year",
                        color = Color.Red
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = stringResource(R.string.select_genres),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6
                )

                LazyHorizontalGrid(
                    modifier = Modifier.height(100.dp),
                    rows = GridCells.Fixed(3)
                ) {
                    items(genreItems.size) { selectedGenreItem ->
                        val genreItem = genreItems[selectedGenreItem]
                        Chip(
                            modifier = Modifier.padding(2.dp),
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (genreItem.isSelected)
                                    colorResource(id = R.color.purple_200)
                                else
                                    colorResource(id = R.color.white)
                            ),
                            onClick = {
                                genreItems = genreItems.map {
                                    if (it.title == genreItem.title) {
                                        genreItem.copy(isSelected = !genreItem.isSelected)
                                    } else {
                                        it
                                    }
                                }
                            }
                        ) {
                            Text(text = genreItem.title.name)
                        }
                    }
                }

                OutlinedTextField(
                    value = director,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { director = it },
                    label = { Text(stringResource(R.string.enter_director)) }
                )
                if (director == ""){
                    Text(
                        text = "Enter Director",
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = actors,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { actors = it },
                    label = { Text(stringResource(R.string.enter_actors)) },
                )
                if (actors == ""){
                    Text(
                        text = "Enter Actors",
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = plot,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    onValueChange = { plot = it },
                    label = {
                        Text(
                            textAlign = TextAlign.Start,
                            text = stringResource(R.string.enter_plot)
                        )
                    }
                )
                if (plot == ""){
                    Text(
                        text = "Enter Plot",
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = rating,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {rating = it
                    },
                    label = { Text(stringResource(R.string.enter_rating)) }
                )
                if (!isRatingValid(rating)) {
                    Text(text = "Enter valid Rating between 0 - 10!",
                    color = Color.Red
                    )
                }


                isEnabledSaveButton = addMovieScreenViewModel.isValidMovie(
                    title,
                    year,
                    genreItems.filter { x -> x.isSelected }.map { x -> x.title },
                    director,
                    actors,
                    rating.toFloatOrNull() ?: 0.0f
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        enabled = isEnabledSaveButton,
                        onClick = { coroutineScope.launch{
                            addMovieScreenViewModel.addMovie(
                                MovieEntity(
                                title = title,
                                year = year,
                                genre = genreItems.filter { x -> x.isSelected }.map { it.title }.toString(),
                                director = director,
                                actors = actors,
                                plot = plot,
                                images = listOf("https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"),
                                rating = rating.toFloatOrNull() ?: 0.0f
                            )) }
                            navController.navigate("home")
                        }) {
                        Text(text = stringResource(R.string.add))
                    }
                }
            }
        }
    }
}

fun isRatingValid(input: String): Boolean {
    if (!isStringValid(input)) {
        return false
    }
    return try {
        val ratingNum = input.toFloat() //überprüft das eine Kommazahl ist
        ratingNum > 0 && ratingNum <= 10 //überprüfung ob im vorgegeben Rahmen
    } catch (ne: NumberFormatException) { //abfangen wenn Buchstaben eingeben werden
        false
    }
}
fun isStringValid(input: String): Boolean { //überprufung nicht leer
    return input.isNotEmpty()
}



