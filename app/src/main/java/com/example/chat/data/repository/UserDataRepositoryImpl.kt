package com.example.chat.data.repository

import com.example.chat.data.models.RemoteMessage
import com.example.chat.data.models.Response
import com.example.chat.domain.repository.UserDataRepository
import com.example.chat.presentation.models.UserData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UserDataRepositoryImpl @Inject constructor(
    val db: FirebaseFirestore
): UserDataRepository {
    override fun updateUserData(data: UserData): Flow<Response<String>> = flow {
        emit(Response.Loading())
        db.collection("users").document(data.id).set(data).await()
        emit(Response.Success("Save successfully"))
    }.catch { error->
        error.message?.let { errorMessage ->
            emit(Response.Failure(errorMessage))
        }
    }
}