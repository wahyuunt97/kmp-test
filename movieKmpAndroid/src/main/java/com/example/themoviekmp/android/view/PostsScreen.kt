package com.example.themoviekmp.android.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.themoviekmp.android.viewModel.PostsViewModel
import com.example.themoviekmp.model.Post
import com.example.themoviekmp.state.DataState


@Composable
fun PostsScreen(viewModel: PostsViewModel) {
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        is DataState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        is DataState.Success<*> -> {
            LazyColumn {
                items(currentState.data as List<Post>) { post ->
                    Text(post.title)
                }
            }
        }
        is DataState.Error -> {
            Text(
                text = "Error: ${currentState.error.message}",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}