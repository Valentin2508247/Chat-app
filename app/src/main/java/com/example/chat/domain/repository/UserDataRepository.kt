package com.example.chat.domain.repository

import com.example.chat.data.models.RemoteMessage
import com.example.chat.data.models.Response
import com.example.chat.presentation.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    fun updateUserData(data: UserData): Flow<Response<String>>
}