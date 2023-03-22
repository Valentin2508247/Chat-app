package com.example.chat.di

import com.example.chat.SomeDependency
import com.example.chat.data.repository.ChatRepositoryImpl
import com.example.chat.data.repository.MessageRepositoryImpl
import com.example.chat.domain.repository.ChatRepository
import com.example.chat.domain.repository.MessageRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    fun provideSomeDependency(): SomeDependency {
        return SomeDependency("Some dependecy info", 17)
    }

    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }
}