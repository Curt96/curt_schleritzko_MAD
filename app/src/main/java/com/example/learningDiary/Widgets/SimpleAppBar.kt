package com.example.learningDiary.Widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SimpleAppBar(title: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                title,
                style = MaterialTheme.typography.h6,
            )

        },
        actions = {
            DropDownAction(navController)

        }
    )
    }

@Composable
fun DropDownAction(navController: NavController) {
    val dropDown = remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = {
            dropDown.value = !dropDown.value
        }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Menu_Icon",
            //tint = Color.Black
        )
    }


        DropdownMenu(expanded = dropDown.value, onDismissRequest = { dropDown.value = false }) {
            DropdownMenuItem(onClick = {
                navController.navigate("Favorites")
                // Toast.makeText(context, "Favorits Clicked", Toast.LENGTH_SHORT).show()
            }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite_icon",
                    //tint = Color.Black
                )
                Text(
                    text = "  Favorites",
                    // color = Color.Black
                )
            }
            DropdownMenuItem(onClick = {
                navController.navigate("addMovie")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Movie")
                Text("Add Movie")
            }
        }
    }
