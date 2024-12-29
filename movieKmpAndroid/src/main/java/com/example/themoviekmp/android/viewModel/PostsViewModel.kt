package com.example.themoviekmp.android.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviekmp.model.Post
import com.example.themoviekmp.repository.PostRepository
import com.example.themoviekmp.state.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: PostRepository
) : ViewModel() {
    private val _state = MutableStateFlow<DataState<List<Post>>>(DataState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            _state.value = DataState.Loading
            repository.getPosts()
                .onSuccess { posts ->
                    _state.value = DataState.Success(posts)
                }
                .onFailure { error ->
                    _state.value = DataState.Error(error)
                }
        }
    }
}
