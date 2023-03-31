package com.example.learningDiary.Widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.learningDiary.models.Movie

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
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
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            tint = MaterialTheme.colors.secondary,
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Add to favorites"
                        )
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