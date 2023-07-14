package com.code.suitmedia.data.resource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.code.suitmedia.data.resource.remote.network.ApiService
import com.code.suitmedia.data.resource.remote.response.DataItem
import com.code.suitmedia.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService,
): IRepository {

    override fun getDataUser(): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                PagingSource(apiService)
            }
        ).flow
    }


}