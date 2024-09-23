package com.example.trading_company_client.domain.repository

import com.example.trading_company_client.data.model.Client
import com.example.trading_company_client.data.model.Order
import com.example.trading_company_client.data.model.requests.AddClientRequest
import com.example.trading_company_client.data.model.requests.AddOrderRequest
import com.example.trading_company_client.data.model.requests.UpdateClientRequest
import com.example.trading_company_client.data.model.requests.UpdateOrderRequest
import com.example.trading_company_client.data.model.response.BaseResponse

interface OrderRepository {

    suspend fun getAllOrders(token: String): List<Order>

    suspend fun addOrder(token: String, addOrderRequest: AddOrderRequest): BaseResponse

    suspend fun updateOrder(
        token: String,
        updateOrderRequest: UpdateOrderRequest
    ): BaseResponse

    suspend fun deleteOrder(token: String, orderId: Int): BaseResponse

    suspend fun fetchOrderByClientNameAndClientLastname(token: String, clientName: String, clientLastname: String): List<Order>
}