package com.example.chat.domain.repository

import com.example.chat.data.models.RemoteMessage
import com.example.chat.data.models.Response
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun loadMessages(chatId: String): Flow<Response<List<RemoteMessage>>>
}