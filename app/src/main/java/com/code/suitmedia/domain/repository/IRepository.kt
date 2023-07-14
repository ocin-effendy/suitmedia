package com.code.suitmedia.domain.repository

import androidx.paging.PagingData
import com.code.suitmedia.data.resource.remote.response.DataItem
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getDataUser(): Flow<PagingData<DataItem>>
}