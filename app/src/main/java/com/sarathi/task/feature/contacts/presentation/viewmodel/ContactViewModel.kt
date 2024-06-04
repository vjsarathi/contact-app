package com.sarathi.task.feature.contacts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarathi.task.feature.contacts.data.repo.ContactsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactsRepo: ContactsRepo
) : ViewModel() {

    init {
        getContacts()
    }

    private fun getContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            contactsRepo.getContactByPage(2).collect {

            }
        }
    }

}