package com.example.chat.presentation.models

data class Message(
    var id: String,
    val userId: String,
    val chatId: String,
    val type: String,
    val info: String
)
