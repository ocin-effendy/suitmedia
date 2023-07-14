package com.code.suitmedia.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.code.suitmedia.data.resource.remote.response.DataItem
import com.code.suitmedia.domain.usecase.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ThirdViewModel @Inject constructor(private val dataUseCase: DataUseCase) : ViewModel() {
    lateinit var dataUser: LiveData<PagingData<DataItem>>

    fun getDataUser(){
        dataUser = dataUseCase.getDataUser().cachedIn(viewModelScope).asLiveData()
    }
}