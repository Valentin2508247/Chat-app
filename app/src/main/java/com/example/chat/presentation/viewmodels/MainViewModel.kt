package com.example.chat.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat.data.models.Response
import com.example.chat.domain.usecases.*
import com.example.chat.presentation.models.Chat
import com.example.chat.presentation.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val createDialogUseCase: CreateChatUseCase,
    private val loadChatsUseCase: LoadChatsUseCase,
    private val loadMessagesUseCase: LoadMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val userDataUseCase: UpdateUserDataUseCase
) : ViewModel() {
    val chats = MutableLiveData<List<Chat>>()

    init {
        loadChats()
    }

    fun updateUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = UserData(
                Firebase.auth.currentUser?.uid!!,
                "Valentin",
                "some image"
            )
            userDataUseCase.execute(data).collect {
                when (it) {
                    is Response.Loading -> {
                        Log.d(TAG, "Loading...")
                    }
                    is Response.Success -> {
                        Log.d(TAG, "Success: ${it.data}")
                    }
                    is Response.Failure -> {
                        Log.d(TAG, "Error: ${it.errorMessage}")
                    }
                }
            }
        }
    }

    fun loadMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            loadMessagesUseCase.execute("chatId").collect {
                when (it) {
                    is Response.Loading -> {
                        Log.d(TAG, "Loading...")
                    }
                    is Response.Success -> {
                        Log.d(TAG, "Success: ${it.data}")
                    }
                    is Response.Failure -> {
                        Log.d(TAG, "Error: ${it.errorMessage}")
                    }
                }
            }
        }
    }

    fun loadChats() {
        viewModelScope.launch(Dispatchers.IO) {
            loadChatsUseCase.execute("userId").collect {
                when (it) {
                    is Response.Loading -> {
                        Log.d(TAG, "Loading...")
                    }
                    is Response.Success -> {
                        Log.d(TAG, "Success: ${it.data}")
                        chats.postValue(
                            it.data.map { remoteChat ->
                                Chat(
                                    remoteChat.id,
                                    emptyList(),
                                    remoteChat.chatName,
                                    remoteChat.imageUrl
                                )
                            }
                        )
                    }
                    is Response.Failure -> {
                        Log.d(TAG, "Error: ${it.errorMessage}")
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "mainViewModel"
    }
}
