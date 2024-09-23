package com.example.trading_company_client.domain.repository

import com.example.trading_company_client.data.model.Client
import com.example.trading_company_client.data.model.requests.AddClientRequest
import com.example.trading_company_client.data.model.requests.UpdateClientRequest
import com.example.trading_company_client.data.model.response.BaseResponse

interface ClientRepository {

    suspend fun getAllClient(token: String): List<Client>

    suspend fun addClient(token: String, addClientRequest: AddClientRequest): BaseResponse

    suspend fun updateClient(
        token: String,
        updateClientRequest: UpdateClientRequest
    ): BaseResponse

    suspend fun deleteClient(token: String, clientId: Int): BaseResponse

    suspend fun fetchClientByNameAndLastname(token: String, name: String, lastname: String): List<Client>
}