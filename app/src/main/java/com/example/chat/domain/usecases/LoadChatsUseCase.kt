package com.example.chat.domain.usecases

import com.example.chat.data.models.RemoteChat
import com.example.chat.data.models.Response
import com.example.chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadChatsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    fun execute(userId: String): Flow<Response<List<RemoteChat>>> {
        return repository.loadChats(userId)
    }
}