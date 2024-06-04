package com.sarathi.task.feature.contacts.data.datasource

import com.sarathi.task.feature.contacts.data.response.ContactResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactService {

    @GET("character/{page}")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<ContactResponse>

}