package com.example.chatbot.presentation.HomePageLayout.Chat

import com.example.chatbot.presentation.IViewsHandling

interface IChatView : IViewsHandling {

    fun openMessageFragment(chatId: String) {}
}