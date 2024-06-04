package com.sarathi.task.core.GenericResponse

sealed class NetworkResponse<T> {

    data class Loading<T>(val isLoading: Boolean) : NetworkResponse<T>()
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class isError<T>(val exception: AppException) : NetworkResponse<T>()

}

class AppException(
    val errCode: Int? = null,
    val errMsg: String? = null
) : Exception()
