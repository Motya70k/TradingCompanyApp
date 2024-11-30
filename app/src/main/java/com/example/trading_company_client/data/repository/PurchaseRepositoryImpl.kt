package com.example.trading_company_client.data.repository

import com.example.trading_company_client.constants.URL
import com.example.trading_company_client.data.model.Purchase
import com.example.trading_company_client.data.model.requests.AddPurchaseRequest
import com.example.trading_company_client.data.model.requests.UpdatePurchaseRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.data.network.ClientProvider
import com.example.trading_company_client.domain.repository.PurchaseRepository
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

class PurchaseRepositoryImpl @Inject constructor(
    private val clientProvider: ClientProvider
) : PurchaseRepository {
    override suspend fun getAllPurchase(token: String): List<Purchase> {
        return try {
            val client = clientProvider.createHttpClient(token)
            val employeesResponse: List<Purchase> =
                client.get(URL.BASE_URL + "get-all-purchases").body()
            employeesResponse
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addPurchase(token: String, addPurchaseRequest: AddPurchaseRequest): BaseResponse {
        return try {
            val client = clientProvider.createHttpClient(token)
            val response: HttpResponse = client.post(URL.BASE_URL + "create-purchase") {
                contentType(ContentType.Application.Json)
                setBody(addPurchaseRequest)
            }
            val responseBody = response.bodyAsText()
            Json.decodeFromString(responseBody)
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
        }
    }

    override suspend fun updatePurchase(token: String, updatePurchaseRequest: UpdatePurchaseRequest): BaseResponse {
        val client = clientProvider.createHttpClient(token)
        val response: HttpResponse = client.post(URL.BASE_URL + "update-purchase") {
            contentType(ContentType.Application.Json)
            setBody(updatePurchaseRequest)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun deletePurchase(token: String, purchaseId: Int): BaseResponse {
        val client = clientProvider.createHttpClient(token)
        val response: HttpResponse = client.delete(URL.BASE_URL + "delete-purchase?id=$purchaseId") {
            contentType(ContentType.Application.Json)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun fetchPurchaseByItemName(token: String, itemName: String): List<Purchase> {
        return try {
            val client = clientProvider.createHttpClient(token)
            val purchasesResponse: List<Purchase> = client.get(URL.BASE_URL + "search-purchase?name=$itemName").body()
            purchasesResponse
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
            emptyList()
        }
    }
}