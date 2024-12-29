package com.example.themoviekmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.themoviekmp.Greeting
import com.example.themoviekmp.android.view.PostsScreen
import com.example.themoviekmp.android.viewModel.PostsViewModel
import com.example.themoviekmp.di.KtorClient
import com.example.themoviekmp.repository.PostRepositoryImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostsScreen(
                viewModel = PostsViewModel(
                    PostRepositoryImpl(KtorClient())
                )
            )
        }
    }
}