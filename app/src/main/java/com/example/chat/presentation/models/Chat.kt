package com.example.chat.presentation.models

data class Chat(
    val id: String,
    val participants: List<String>,
    val name: String,
    val imageUrl: String?
)