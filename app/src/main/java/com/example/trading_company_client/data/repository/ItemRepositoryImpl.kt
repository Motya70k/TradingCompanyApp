package com.example.trading_company_client.data.repository

import com.example.trading_company_client.constants.URL
import com.example.trading_company_client.constants.URL.BASE_URL
import com.example.trading_company_client.data.model.Item
import com.example.trading_company_client.data.model.requests.AddItemRequest
import com.example.trading_company_client.data.model.requests.UpdateItemRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.data.network.ClientProvider
import com.example.trading_company_client.domain.repository.ItemRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.delete
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val clientProvider: ClientProvider
) : ItemRepository {

    override suspend fun getAllItems(token: String): List<Item> {
        return try {
            val client = clientProvider.createHttpClient(token)
            val itemsResponse: List<Item> =
                client.get(BASE_URL + "get-all-items").body()
            itemsResponse
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addItem(token: String, addItemRequest: AddItemRequest): BaseResponse {
        return try {
            val client = clientProvider.createHttpClient(token)
            val response: HttpResponse = client.post(URL.BASE_URL + "create-item") {
                contentType(ContentType.Application.Json)
                setBody(addItemRequest)
            }
            val responseBody = response.bodyAsText()
            Json.decodeFromString(responseBody)
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
        }
    }

    override suspend fun updateItem(token:String, item: UpdateItemRequest): BaseResponse {
        val client = clientProvider.createHttpClient(token)
        val response: HttpResponse = client.post(URL.BASE_URL + "update-item") {
            contentType(ContentType.Application.Json)
            setBody(item)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }
    override suspend fun deleteItem(token: String, itemId: Int): BaseResponse {
        val client = clientProvider.createHttpClient(token)
        val response: HttpResponse = client.delete(BASE_URL + "delete-item?id=$itemId") {
            contentType(ContentType.Application.Json)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun fetchItemByName(token: String, itemName: String): List<Item> {
        return try {
            val client = clientProvider.createHttpClient(token)
            val purchasesResponse: List<Item> = client.get(URL.BASE_URL + "search-item?name=$itemName").body()
            purchasesResponse
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
            emptyList()
        }
    }
}