package com.example.chatbot.domain.use_case

import com.example.chatbot.domain.model.ModelMessage
import com.example.chatbot.domain.repository.UserRepository
import javax.inject.Inject

class ChatUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    fun getAllChats(userId: String) = userRepository.getAllChats(userId)

    fun getAllMessagesOfChat(chatId : String) = userRepository.getAllMessagesOfChat(chatId)
    fun sendMessage(chatId: String, messageModel: ModelMessage) = userRepository.sendMessage(chatId, messageModel)
}