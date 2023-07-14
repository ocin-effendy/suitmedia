package com.code.suitmedia.domain.usecase

import androidx.paging.PagingData
import com.code.suitmedia.data.resource.remote.response.DataItem
import com.code.suitmedia.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataInteractor @Inject constructor(private val repository: IRepository): DataUseCase {
    override fun getDataUser(): Flow<PagingData<DataItem>> = repository.getDataUser()
}