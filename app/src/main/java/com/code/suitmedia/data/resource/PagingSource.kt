package com.code.suitmedia.data.resource

import com.code.suitmedia.data.resource.remote.network.ApiService
import com.code.suitmedia.data.resource.remote.response.DataItem
import androidx.paging.PagingSource
import androidx.paging.PagingState

class PagingSource(
    private val apiService: ApiService
) : PagingSource<Int, DataItem>() {


    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val response = apiService.getUsers(
                position,
                params.loadSize,
            )

            val listStory = response.data ?: emptyList()

            LoadResult.Page(
                data = listStory.filterNotNull(),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (listStory.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}