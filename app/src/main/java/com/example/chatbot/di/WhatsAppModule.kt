package com.example.chatbot.di

import android.content.Context
import androidx.room.Room
import com.example.chatbot.data.database.UserDao
import com.example.chatbot.data.database.UserDataBase
import com.example.chatbot.data.repository.UserRepositoryImpl
import com.example.chatbot.domain.repository.AuthRepository
import com.example.chatbot.domain.repository.UserRepository
import com.example.chatbot.domain.use_case.AuthenticationUseCase
import com.example.chatbot.domain.use_case.ContactsUseCase
import com.example.chatbot.util.Constants
import com.example.chatbot.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDatabase(@ApplicationContext context: Context): UserDataBase {
        return Room.databaseBuilder(
            context,
            UserDataBase::class.java,
            Constants.appRoomDatabaseName
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDataBase): UserDao {
        return database.userDao()
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

    @Provides
    @Singleton
    fun provideUserRepository(
        firestore: FirebaseFirestore,
        userDao: UserDao,
    ): UserRepository {
        return UserRepositoryImpl(firestore, userDao)
    }

    @Provides
    @Singleton
    fun provideContactsUseCase(repository: UserRepository) : ContactsUseCase {
        return ContactsUseCase(repository)
    }

}