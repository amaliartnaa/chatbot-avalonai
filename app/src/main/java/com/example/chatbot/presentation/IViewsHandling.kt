package com.example.chatbot.presentation

interface IViewsHandling {

    fun hideProgressBar() {}
    fun showProgressBar() {}
    fun showError(error : String) {}
    fun changeViewsVisibility() {}
    fun showHomePage() {}
    fun dismissOtpBottomSheetDialogFragment() {}
    fun getUserId() : String
}