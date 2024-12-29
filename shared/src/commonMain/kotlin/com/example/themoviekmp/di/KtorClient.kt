package com.example.themoviekmp.di

import com.example.themoviekmp.model.Post
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    suspend fun getPosts(): List<Post> {
        return client.get("$BASE_URL/posts").body()
    }
}