package com.example.chat.data.repository

import com.example.chat.data.models.Response
import com.example.chat.data.models.RemoteMessage
import com.example.chat.domain.repository.MessageRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
): MessageRepository {
    override fun loadMessages(chatId: String): Flow<Response<List<RemoteMessage>>> = flow {
        emit(Response.Loading())
        val query = db.collection("messages").whereEqualTo("chat","chatId")
        val result = query.get().await().documents.mapNotNull { doc ->
            doc.toObject(RemoteMessage::class.java)
        }
        emit(Response.Success(result))
    }.catch { error->
        error.message?.let { errorMessage ->
            emit(Response.Failure(errorMessage))
        }
    }
}