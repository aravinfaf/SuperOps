package com.aravind.superops.data.repository

import com.aravind.superops.data.api.ApiService
import com.aravind.superops.data.model.AuthorResponseModel
import javax.inject.Inject

class AuthorRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAuthorDetails() : AuthorResponseModel {
      return  apiService.getAuthorDetails()
    }
}