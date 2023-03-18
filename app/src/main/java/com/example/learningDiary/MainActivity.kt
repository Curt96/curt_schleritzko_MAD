package com.example.learningDiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.learningDiary.navigation.Navigation
import com.example.learningDiary.ui.theme.LectureExamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LectureExamplesTheme {
                Navigation()
            }
        }
    }
}


