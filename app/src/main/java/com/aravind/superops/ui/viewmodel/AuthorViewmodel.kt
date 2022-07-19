package com.aravind.superops.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aravind.superops.data.api.ApiService
import com.aravind.superops.data.model.AuthorResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorViewmodel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    val authLiveData = MutableLiveData<AuthorResponseModel>()

    fun getAuthorDetails(){
        viewModelScope.launch {
            authLiveData.postValue(apiService.getAuthorDetails())
        }
    }
}