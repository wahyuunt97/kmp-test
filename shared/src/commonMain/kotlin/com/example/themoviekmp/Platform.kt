package com.example.themoviekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform