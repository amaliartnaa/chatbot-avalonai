package com.example.chatbot.domain.repository

import android.service.autofill.UserData
import com.example.chatbot.domain.model.User
import com.example.chatbot.presentation.MainActivity
import com.example.chatbot.util.Resource
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun phoneNumberSignIn(phoneNumber : String,activity : MainActivity) : Flow<Resource<Boolean>>

    fun isUserAuthenticated() : Boolean

    fun getUserId() : String

    suspend fun signInWithAuthCredential(phoneAuthCredential: PhoneAuthCredential) : Resource<Boolean>

    fun createUserProfile(user: User): Flow<Resource<Boolean>>
}