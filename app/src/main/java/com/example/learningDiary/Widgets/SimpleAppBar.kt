package com.example.learningDiary.Widgets

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    }
}