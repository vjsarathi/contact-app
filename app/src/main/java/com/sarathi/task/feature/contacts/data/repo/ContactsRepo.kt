package com.sarathi.task.feature.contacts.data.repo

import com.sarathi.task.core.GenericResponse.NetworkResponse
import com.sarathi.task.feature.contacts.data.response.Result
import kotlinx.coroutines.flow.Flow

interface ContactsRepo {

    suspend fun getContactByPage(page: Int) : Flow<NetworkResponse<Result>>

}