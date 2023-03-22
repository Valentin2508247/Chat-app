package com.example.chat.presentation.recyclerview

import com.example.chat.presentation.models.Chat

interface ChatListener {
    fun onOpenChat(chat: Chat)
}