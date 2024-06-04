package com.sarathi.task.feature.contacts.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sarathi.task.core.GenericResponse.AppException
import com.sarathi.task.core.GenericResponse.NetworkResponse
import com.sarathi.task.feature.contacts.data.ContactPagingDataSource
import com.sarathi.task.feature.contacts.data.datasource.ContactService
import com.sarathi.task.feature.contacts.data.repo.ContactsRepo
import com.sarathi.task.feature.contacts.data.response.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ContactRepoImpl @Inject constructor(
    private val contactService: ContactService
) : ContactsRepo {

   /* override suspend fun getContactByPage(page: Int): Flow<NetworkResponse<Result>> =
        flow<NetworkResponse<Result>> {
            val result = contactService.getCharacters(page)
            if (!result.isSuccessful) {
                NetworkResponse.isError<AppException>(handleException(result.code()))
            } else {
                NetworkResponse.Success(data = result.body())
            }
        }.catch {
            NetworkResponse.isError<AppException>(
                AppException(
                    errMsg = it.localizedMessage ?: "Something went wrong",
                    errCode = null
                )
            )
        }.onStart {
            NetworkResponse.Loading<Boolean>(true)
        }.onCompletion {
            NetworkResponse.Loading<Boolean>(false)
        }*/

    private fun handleException(code: Int): AppException {
        return when (code) {
            401 -> AppException(errCode = 401, errMsg = "UnAuthorized")
            else -> AppException(errCode = null, errMsg = "Something went wrong")
        }
    }

    override suspend fun getContactByPage(page: Int): Flow<NetworkResponse<Result>> = flow {
        Pager(
            config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = { ContactPagingDataSource(contactService) }
        )
    }

}