package com.example.chatbot.presentation.HomePageLayout.Contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.domain.model.User
import com.example.chatbot.domain.use_case.ContactsUseCase
import com.example.chatbot.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private var contactsUseCase: ContactsUseCase,
) : ViewModel() {

    private val _whatsAppContactsList = MutableStateFlow<List<User>>(emptyList())
    val whatsAppContactsList : StateFlow<List<User>> = _whatsAppContactsList

    private lateinit var iContactViews : IContactsViews

    fun getAllWhatsAppContacts(deviceContacts: List<String>,interfaceListener : IContactsViews) = viewModelScope.launch {
        iContactViews = interfaceListener
        contactsUseCase.getAllWhatsAppContacts(deviceContacts).collectLatest {
            when(it) {
                is Resource.Success -> {
                    iContactViews.hideProgressBar()
                    _whatsAppContactsList.value = it.data
                }
                is Resource.Loading -> {
                    iContactViews.showProgressBar()
                }
                is Resource.Error -> {
                    iContactViews.showError(it.message?:"An Error Occurred")
                }
            }
        }
    }
}