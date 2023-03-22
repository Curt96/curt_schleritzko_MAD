package com.example.learningDiary.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.learningDiary.R
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies
import com.example.learningDiary.ui.theme.Purple200
import com.example.learningDiary.ui.theme.Purple500
import java.util.Random


@Composable
fun HomeScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            AppBar("Movie App")
            Greeting()
            Text(
                style = MaterialTheme.typography.h6,
                text= "Movie List"
            )
            MyList(navController)
        }
        //MyList()
        //Greeting()
        //WelcomeText(modifier = Modifier.padding(16.dp), text = "welcome to my app!")
    }
}

@Preview
@Composable
fun MyList(navController: NavController = rememberNavController(),
           movies: List<Movie> = getMovies()){
    LazyColumn{
        items(movies) {movie ->
            MovieRow(
                movie = movie,
            )  { movieId ->
                Log.d("MyList", "item clicked $movieId")
                // navigate to detailscreen
                navController.navigate("detail/$movieId")
            }
        }
    }
}


@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var expandDetails by remember {
        mutableStateOf(false)
    }
    val moviePicture =

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(movie.images[0]),
                    modifier = Modifier.fillMaxWidth(),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
                Icon(
                    imageVector = if (expandDetails == true)Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Show details",
                    modifier = Modifier.clickable( onClick = { expandDetails = !expandDetails})
                )
            }
            if (expandDetails) {
                Spacer(modifier = Modifier.size(20.dp))
                Text("Director: ${movie.director}")
                Text("Released: ${movie.year}")
                Text("Genre: ${movie.genre}")
                Text("Actors: ${movie.actors}")
                Text("Rating: ${movie.rating}")
                Spacer(modifier = Modifier.size(5.dp))
                Divider(startIndent = 5.dp, thickness = 1.dp, color = Color.DarkGray)
                Text("Plot: ${movie.plot}", Modifier.padding(10.dp),)
            }
        }
    }
}

@Preview
@Composable
fun WelcomeText(modifier: Modifier = Modifier, text: String = "default") {
    Row(
        modifier = modifier
            .padding(16.dp)
            .background(Color.Blue)
            .fillMaxWidth()
    ) {
        Text(modifier = modifier, text = "Hola")
        Text(text = text)
    }

}

@Preview
@Composable
fun Greeting() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember {
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it},
            label = { Text("Name")}
        )




        /*
        // step 2 - add a mutableStateOf to fire the event for recomposition

       var name = mutableStateOf("")   // use a state holder to register changes
        // var name  by mutableStateOf("")
        Text(text = "Hello ${name.value}!")   // get value of state holder object

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },    // change its value accordingly
            label = { Text("Name")}
        )
        */



        /*
        // step 3 - use remember
        var name by remember {         // use remember to skip overwriting after first composition
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name")}
        )

         */
    }
}
@Composable
fun AppBar(title: String = "Movie List") {
        TopAppBar(
            //modifier = Modifier.padding(top = 24.dp),
            //backgroundColor = Color.Magenta,
            //elevation = 0.dp,
            title = {
                Text(
                    title,
                    style = MaterialTheme.typography.h6,
                )

            },
            actions = {
                DropDownAction()

            }
        )
    }
@Composable
fun DropDownAction() {
    val context = LocalContext.current
    val dropDown = remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = { dropDown.value = !dropDown.value
        }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Menu_Icon",
            //tint = Color.Black
        )
    }
    DropdownMenu(expanded = dropDown.value, onDismissRequest = { dropDown.value = false }) {
        DropdownMenuItem(onClick = {Toast.makeText(context, "Favorits Clicked", Toast.LENGTH_SHORT).show()}) {
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