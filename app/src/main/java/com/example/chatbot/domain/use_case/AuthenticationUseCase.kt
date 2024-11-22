package com.example.chatbot.domain.use_case

import com.example.chatbot.domain.model.User
import com.example.chatbot.domain.repository.AuthRepository
import com.example.chatbot.presentation.MainActivity
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    fun phoneNumberSignIn(phoneNumber: String, activity: MainActivity) =
        authRepository.phoneNumberSignIn(activity = activity, phoneNumber = phoneNumber)

    fun isUserAuthenticated() = authRepository.isUserAuthenticated()

    fun getUserId() = authRepository.getUserId()

    fun createProfile(user: User) = authRepository.createUserProfile(user)
}