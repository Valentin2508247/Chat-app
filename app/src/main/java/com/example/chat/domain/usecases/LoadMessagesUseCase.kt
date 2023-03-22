package com.example.chat.domain.usecases

import com.example.chat.data.models.Response
import com.example.chat.data.models.RemoteMessage
import com.example.chat.data.repository.MessageRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadMessagesUseCase @Inject constructor(
    private val repository: MessageRepositoryImpl
) {
    fun execute(chatId: String): Flow<Response<List<RemoteMessage>>> {
        return repository.loadMessages(chatId)
    }
}