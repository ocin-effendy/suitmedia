package com.code.suitmedia.domain.usecase

import androidx.paging.PagingData
import com.code.suitmedia.data.resource.remote.response.DataItem
import kotlinx.coroutines.flow.Flow

interface DataUseCase {
 fun getDataUser(): Flow<PagingData<DataItem>>
}