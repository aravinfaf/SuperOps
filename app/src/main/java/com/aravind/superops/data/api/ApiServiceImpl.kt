package com.aravind.superops.data.api

import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    suspend fun getAuthorDetails() = apiService.getAuthorDetails()
}