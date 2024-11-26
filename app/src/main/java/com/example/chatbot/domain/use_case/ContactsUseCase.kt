package com.example.chatbot.domain.use_case

import com.example.chatbot.domain.model.User
import com.example.chatbot.domain.repository.UserRepository
import com.example.chatbot.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun getAllWhatsAppContacts(deviceContacts: List<String>): Flow<Resource<List<User>>> {
        return userRepository.getAllContacts(deviceContacts)
    }
}