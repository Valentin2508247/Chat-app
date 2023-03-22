package com.example.chat.domain.repository

import com.example.chat.data.models.RemoteChat
import com.example.chat.data.models.Response
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun loadChats(userId: String): Flow<Response<List<RemoteChat>>>
}