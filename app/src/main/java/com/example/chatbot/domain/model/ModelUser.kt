package com.example.chatbot.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val userId: String,
    val userName: String?,
    val userNumber: String?,
    val userImage: String?,
    val userStatus: String?,
)