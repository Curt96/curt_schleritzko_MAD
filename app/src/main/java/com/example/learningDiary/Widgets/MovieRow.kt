package com.example.learningDiary.Widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.learningDiary.DataRoom.Entities.Movie
import com.example.learningDiary.DataRoom.Entities.MovieEntity
import com.example.learningDiary.viewModels.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun MovieRow(
    movie: MovieEntity,
    favorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit,
    onItemClick: (Int) -> Unit = {},
) {
    var expandDetails by remember {
        mutableStateOf(false)
    }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable { onItemClick(movie.id) },
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 5.dp
        ) {
            Column {
                Box(
                    modifier = androidx.compose.ui.Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(movie.images[0]),
                        modifier = Modifier.fillMaxWidth(),
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .height(25.dp)
                            .padding(2.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        IconButton(
                            onClick = { onFavoriteChange(movie.isFavorite) }
                        ) {
                            Icon(
                                tint = MaterialTheme.colors.secondary,
                                imageVector =
                                if (movie.isFavorite) {
                                    Icons.Default.Favorite
                                } else {
                                    Icons.Default.FavoriteBorder
                                },
                                contentDescription = "Add to favorites"
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(movie.title, style = MaterialTheme.typography.h6)
                    Icon(
                        imageVector = if (expandDetails == true) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Show details",
                        modifier = Modifier.clickable(onClick = { expandDetails = !expandDetails })
                    )
                }
                if (expandDetails) {
                    Spacer(modifier = androidx.compose.ui.Modifier.size(20.dp))
                    Text("Director: ${movie.director}")
                    Text("Released: ${movie.year}")
                    Text("Genre: ${movie.genre}")
                    Text("Actors: ${movie.actors}")
                    Text("Rating: ${movie.rating}")
                    Spacer(modifier = androidx.compose.ui.Modifier.size(5.dp))
                    Divider(startIndent = 5.dp, thickness = 1.dp, color = Color.DarkGray)
                    Text("Plot: ${movie.plot}", androidx.compose.ui.Modifier.padding(10.dp),)
                }
            }
        }
}
@Composable
fun HorizontalImageView(movieEntity: MovieEntity) {
    LazyRow {
        items(movieEntity.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie poster",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}