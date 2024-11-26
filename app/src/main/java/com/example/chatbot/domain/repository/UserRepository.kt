package com.example.chatbot.domain.repository

import com.example.chatbot.domain.model.ModelChat
import com.example.chatbot.domain.model.ModelMessage
import com.example.chatbot.domain.model.User
import com.example.chatbot.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllContacts(deviceContacts : List<String>) : Flow<Resource<List<User>>>

    fun getAllChats(userId : String) : Flow<Resource<List<ModelChat>>>

    fun getAllMessagesOfChat(chatId : String) : Flow<Resource<List<ModelMessage>>>
    fun sendMessage(chatId: String, messageModel: ModelMessage): Flow<Resource<Boolean>>
}