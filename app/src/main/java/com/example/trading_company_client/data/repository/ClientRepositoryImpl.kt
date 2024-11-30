package com.example.trading_company_client.data.repository

import com.example.trading_company_client.constants.URL
import com.example.trading_company_client.data.model.Client
import com.example.trading_company_client.data.model.requests.AddClientRequest
import com.example.trading_company_client.data.model.requests.UpdateClientRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.data.network.ClientProvider
import com.example.trading_company_client.domain.repository.ClientRepository
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(
    private val clientProvider: ClientProvider
) : ClientRepository {
    override suspend fun getAllClient(token: String): List<Client> {
        return try {
            val client = clientProvider.createHttpClient(token)
            val clientResponse: List<Client> =
                client.get(URL.BASE_URL + "get-all-clients").body()
            clientResponse
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addClient(
        token: String,
        addClientRequest: AddClientRequest
    ): BaseResponse {
        return try {
            val client = clientProvider.createHttpClient(token)
            val response: HttpResponse = client.post(URL.BASE_URL + "add-client") {
                contentType(ContentType.Application.Json)
                setBody(addClientRequest)
            }
            val responseBody = response.bodyAsText()
            Json.decodeFromString(responseBody)
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
        }
    }

    override suspend fun updateClient(
        token: String,
        updateClientRequest: UpdateClientRequest
    ): BaseResponse {
        val client = clientProvider.createHttpClient(token)
        val response: HttpResponse = client.post(URL.BASE_URL + "update-client") {
            contentType(ContentType.Application.Json)
            setBody(updateClientRequest)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun deleteClient(token: String, clientId: Int): BaseResponse {
        val client = clientProvider.createHttpClient(token)
        val response: HttpResponse = client.delete(URL.BASE_URL + "delete-client?id=$clientId") {
            contentType(ContentType.Application.Json)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun fetchClientByNameAndLastname(
        token: String,
        name: String,
        lastname: String
    ): List<Client> {
        return try {
            val client = clientProvider.createHttpClient(token)
            val clientsResponse: List<Client> = client.get(URL.BASE_URL + "search-client?name=$name&lastname=$lastname").body()
            clientsResponse
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
            emptyList()
        }
    }
}