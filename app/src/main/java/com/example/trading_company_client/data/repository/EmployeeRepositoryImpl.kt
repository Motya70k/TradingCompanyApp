package com.example.trading_company_client.data.repository

import androidx.annotation.OptIn
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.trading_company_client.constants.URL
import com.example.trading_company_client.data.model.Employee
import com.example.trading_company_client.data.model.requests.RegisterRequest
import com.example.trading_company_client.data.model.requests.UpdateClientRequest
import com.example.trading_company_client.data.model.requests.UpdateEmployeeRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.domain.repository.EmployeeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class EmployeeRepositoryImpl : EmployeeRepository {
    @OptIn(UnstableApi::class)
    override suspend fun getAllEmployees(token: String): List<Employee> {
        return try {
            val client = createHttpClient(token)
            val employeesResponse: List<Employee> = client.get(URL.BASE_URL + "get-all-employees").body()
            employeesResponse
        } catch (e: Exception) {
            Log.e("EmployeeRepository", "Error fetching employees", e)
            BaseResponse(false, e.message ?: "Unknown message")
            emptyList()
        }
    }

    override suspend fun registerEmployee(token: String, registerRequest: RegisterRequest): BaseResponse {
        return try {
            val client = createHttpClient(token)
            val response: HttpResponse = client.post(URL.BASE_URL + "register") {
                contentType(ContentType.Application.Json)
                setBody(registerRequest)
            }
            val responseBody = response.bodyAsText()
            Json.decodeFromString(responseBody)
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
        }
    }

    override suspend fun updateEmployee(
        token: String,
        updateEmployeeRequest: UpdateEmployeeRequest
    ): BaseResponse {
        val client = createHttpClient(token)
        val response: HttpResponse = client.post(URL.BASE_URL + "update-employee") {
            contentType(ContentType.Application.Json)
            setBody(updateEmployeeRequest)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun deleteEmployee(token: String, employeeId: Int): BaseResponse {
        val client = createHttpClient(token)
        val response: HttpResponse = client.delete(URL.BASE_URL + "delete-employee?id=$employeeId") {
            contentType(ContentType.Application.Json)
        }
        val responseBody = response.bodyAsText()
        return Json.decodeFromString(responseBody)
    }

    override suspend fun fetchEmployeeByNameAndLastname(
        token: String,
        name: String,
        lastname: String
    ): List<Employee> {
        return try {
            val client = createHttpClient(token)
            val employeesResponse: List<Employee> = client.get(URL.BASE_URL + "search-employee?name=$name&lastname=$lastname").body()
            employeesResponse
        } catch (e: Exception) {
            BaseResponse(false, e.message ?: "Unknown message")
            emptyList()
        }
    }

    private fun createHttpClient(token: String): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens (
                            accessToken = token,
                            refreshToken = token
                        )
                    }
                }
            }
        }
    }
}