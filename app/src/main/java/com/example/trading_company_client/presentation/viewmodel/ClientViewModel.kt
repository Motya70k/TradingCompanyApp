package com.example.trading_company_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trading_company_client.data.model.Client
import com.example.trading_company_client.data.model.requests.AddClientRequest
import com.example.trading_company_client.data.model.requests.UpdateClientRequest
import com.example.trading_company_client.domain.usecase.ClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val clientUseCase: ClientUseCase
) : ViewModel() {

    private val _clients = MutableLiveData<List<Client>>()
    val clients: LiveData<List<Client>> get() = _clients

    private fun loadClients(token: String) {
        viewModelScope.launch {
            try {
                val clientList = clientUseCase.getAllClient(token)
                _clients.postValue(clientList)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun addClient(token: String, addClientRequest: AddClientRequest) {
        viewModelScope.launch {
            try {
                clientUseCase.addClient(token, addClientRequest)
                loadClients(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun updateClient(token: String, updateClientRequest: UpdateClientRequest) {
        viewModelScope.launch {
            try {
                clientUseCase.updateClient(token, updateClientRequest)
                loadClients(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun deleteClient(token: String, clientId: Int) {
        viewModelScope.launch {
            try {
                clientUseCase.deleteClient(token, clientId)
                loadClients(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun searchClient(token: String, name: String, lastname: String) {
        viewModelScope.launch {
            try {
                val clients = clientUseCase.fetchClientByNameAndLastname(token, name, lastname)
                _clients.postValue(clients)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }
}