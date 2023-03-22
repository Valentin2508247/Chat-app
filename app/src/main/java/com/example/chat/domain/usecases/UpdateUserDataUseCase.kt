package com.example.chat.domain.usecases

import com.example.chat.data.models.Response
import com.example.chat.domain.repository.UserDataRepository
import com.example.chat.presentation.models.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserDataUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    fun execute(data: UserData): Flow<Response<String>> {
        return repository.updateUserData(data)
    }
}