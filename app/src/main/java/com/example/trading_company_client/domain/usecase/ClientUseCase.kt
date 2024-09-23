package com.example.trading_company_client.domain.usecase

import com.example.trading_company_client.data.model.Client
import com.example.trading_company_client.data.model.requests.AddClientRequest
import com.example.trading_company_client.data.model.requests.UpdateClientRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.domain.repository.ClientRepository

class ClientUseCase(
    private val clientRepository: ClientRepository
) {

    suspend fun getAllClient(token: String): List<Client> {
        return clientRepository.getAllClient(token)
    }

    suspend fun addClient(token: String, addClientRequest: AddClientRequest): BaseResponse {
        return clientRepository.addClient(token, addClientRequest)
    }

    suspend fun updateClient(
        token: String,
        updateClientRequest: UpdateClientRequest
    ): BaseResponse {
        return clientRepository.updateClient(token, updateClientRequest)
    }

    suspend fun deleteClient(token: String, clientId: Int): BaseResponse {
        return clientRepository.deleteClient(token, clientId)
    }

    suspend fun fetchClientByNameAndLastname(token: String, name: String, lastname: String): List<Client> {
        return clientRepository.fetchClientByNameAndLastname(token, name, lastname)
    }
}