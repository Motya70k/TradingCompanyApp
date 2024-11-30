package com.example.trading_company_client.data.repository

import com.example.trading_company_client.constants.URL
import com.example.trading_company_client.data.model.Order
import com.example.trading_company_client.data.model.requests.AddOrderRequest
import com.example.trading_company_client.data.model.requests.UpdateOrderRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.data.network.ClientProvider
import com.example.trading_company_client.domain.repository.OrderRepository
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

class OrderRepositoryImpl @Inject constructor(
    private val clientProvider: ClientProvider
) : OrderRepository {
    override suspend fun getAllOrders(token: String): List<Order> {
        return try {
            val client = clientProvider.createHttpClient(token)
            val orderResponse: List<Order> =
                client.get(URL.BASE_URL + "get-all-orders").body()
            orderResponse
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addOrder(token: String, addOrderRequest: AddOrderRequest): BaseResponse {
        return try {
            val client = clientProvider.createHttpClient(token)
            val response: HttpResponse = client.post(URL.BASE_URL + "create-order") {
                contentType(ContentType.Application.Json)
                setBody(addOrderRequest)
            }
            val responseBody = response.bodyAsText()
            Json.decodeFromString(responseBody)
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
        }
    }

    override suspend fun updateOrder(
        token: String,
        updateOrderRequest: UpdateOrderRequest
    ): BaseResponse {
        val client = clientProvider.createHttpClient(token)
        val response: HttpResponse = client.post(URL.BASE_URL + "update-order") {
            contentType(ContentType.Application.Json)
            setBody(updateOrderRequest)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun deleteOrder(token: String, orderId: Int): BaseResponse {
        val client = clientProvider.createHttpClient(token)
        val response: HttpResponse = client.delete(URL.BASE_URL + "delete-order?id=$orderId") {
            contentType(ContentType.Application.Json)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun fetchOrderByClientNameAndClientLastname(
        token: String,
        clientName: String,
        clientLastname: String
    ): List<Order> {
        return try {
            val client = clientProvider.createHttpClient(token)
            val clientsResponse: List<Order> = client.get(URL.BASE_URL + "search-order?name=$clientName&lastname=$clientLastname").body()
            clientsResponse
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
            emptyList()
        }
    }
}