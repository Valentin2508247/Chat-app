package com.example.chat.data.repository

import com.example.chat.data.models.RemoteChat
import com.example.chat.data.models.Response
import com.example.chat.domain.repository.ChatRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
): ChatRepository {
    override fun loadChats(userId: String): Flow<Response<List<RemoteChat>>> = flow {
        emit(Response.Loading())
        val idQuery = db.collection("chatUsers").whereEqualTo("userId",userId)
        val ids = idQuery.get().await().mapNotNull {
            it.data["chatId"].toString()
        }
        val chatQuery = db.collection("chats").whereIn("id", ids)
        val result = chatQuery.get().await().mapNotNull { doc ->
            doc.toObject(RemoteChat::class.java)
        }
        emit(Response.Success(result))
    }.catch { error->
        error.message?.let { errorMessage ->
            emit(Response.Failure(errorMessage))
        }
    }
}