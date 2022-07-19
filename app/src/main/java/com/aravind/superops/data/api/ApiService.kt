package com.aravind.superops.data.api

import com.aravind.superops.data.model.AuthorResponseModel
import retrofit2.http.GET

interface ApiService {

    @GET("messages")
    suspend fun getAuthorDetails() : AuthorResponseModel
}