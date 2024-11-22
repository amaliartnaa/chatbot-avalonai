package com.example.chatbot.di

import androidx.test.espresso.core.internal.deps.dagger.Provides
import com.example.chatbot.domain.repository.AuthRepository
import com.example.chatbot.domain.use_case.AuthenticationUseCase
import com.example.whatsapp.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WhatsAppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, firebaseFirestore)
    }


    @Provides
    @Singleton
    fun provideAuthenticationUseCase(authRepository: AuthRepository) : AuthenticationUseCase {
        return AuthenticationUseCase(authRepository)
    }
}