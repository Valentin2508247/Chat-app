package com.example.chat.di

import com.example.chat.data.repository.ChatRepositoryImpl
import com.example.chat.data.repository.MessageRepositoryImpl
import com.example.chat.data.repository.UserDataRepositoryImpl
import com.example.chat.domain.repository.ChatRepository
import com.example.chat.domain.repository.MessageRepository
import com.example.chat.domain.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMessageRepository(repo: MessageRepositoryImpl): MessageRepository

    @Binds
    abstract fun provideChatRepository(repo: ChatRepositoryImpl): ChatRepository

    @Binds
    abstract fun provideUserDataRepository(repo: UserDataRepositoryImpl): UserDataRepository
}