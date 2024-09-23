package com.example.trading_company_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trading_company_client.utils.TokenStorage
import com.example.trading_company_client.data.model.requests.LoginRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.domain.usecase.LoginRequestUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRequestUseCase: LoginRequestUseCase,
    private val tokenStorage: TokenStorage
): ViewModel() {
    private val _loginResponse = MutableLiveData<BaseResponse?>()
    val loginResponse: LiveData<BaseResponse?> get() = _loginResponse

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = loginRequestUseCase.employeeLogin(loginRequest)
                _loginResponse.postValue(response)
                if (response?.success == true) {
                    tokenStorage.saveToken(response.token.toString())
                } else {
                    _error.postValue(response?.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            }
        }
    }
    fun clearError() {
        _error.postValue(null)
    }
}