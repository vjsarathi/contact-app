package com.sarathi.task.feature.contacts.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sarathi.task.feature.contacts.data.datasource.ContactService
import com.sarathi.task.feature.contacts.data.response.ContactResponse
import com.sarathi.task.feature.contacts.data.response.Result

class ContactPagingDataSource(
    private val contactService: ContactService
): PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position = params.key ?: 1
            val response = contactService.getCharacters(position)

            if (response.isSuccessful && response.body() != null) {
                LoadResult.Page(
                    data = response.body()?.results ?: listOf(),
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == (response.body() as ContactResponse).info.pages) null else (position + 1)
                )
            } else {
                LoadResult.Error(throw Exception("No Response"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}