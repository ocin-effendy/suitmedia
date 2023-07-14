package com.code.suitmedia.data.resource.remote.network

import com.code.suitmedia.data.resource.remote.response.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int)
    : ResponseData
}