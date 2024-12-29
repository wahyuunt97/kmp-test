package com.example.themoviekmp.repository

import com.example.themoviekmp.di.KtorClient
import com.example.themoviekmp.model.Post

interface PostRepository {
    suspend fun getPosts(): Result<List<Post>>
}

class PostRepositoryImpl(
    private val api: KtorClient
) : PostRepository {
    override suspend fun getPosts(): Result<List<Post>> = runCatching {
        api.getPosts()
    }
}