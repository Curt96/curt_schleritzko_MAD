package com.example.learningDiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learningDiary.navigation.Navigation
import com.example.learningDiary.ui.theme.LectureExamplesTheme
import com.example.learningDiary.viewModels.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieViewModel: MovieViewModel by viewModels()
        setContent {
            LectureExamplesTheme {
                Navigation(movieViewModel)
            }
        }
    }
}


