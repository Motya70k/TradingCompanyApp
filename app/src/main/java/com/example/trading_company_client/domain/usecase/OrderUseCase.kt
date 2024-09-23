package com.example.trading_company_client.domain.usecase

import com.example.trading_company_client.data.model.Order
import com.example.trading_company_client.data.model.requests.AddOrderRequest
import com.example.trading_company_client.data.model.requests.UpdateOrderRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.domain.repository.OrderRepository

class OrderUseCase(
    private val orderRepository: OrderRepository
) {

    suspend fun getAllOrders(token: String): List<Order> {
        return orderRepository.getAllOrders(token)
    }

    suspend fun addOrder(token: String, addOrderRequest: AddOrderRequest): BaseResponse {
        return orderRepository.addOrder(token, addOrderRequest)
    }

    suspend fun updateOrder(
        token: String,
        updateOrderRequest: UpdateOrderRequest
    ): BaseResponse {
        return orderRepository.updateOrder(token, updateOrderRequest)
    }

    suspend fun deleteOrder(token: String, orderId: Int): BaseResponse {
        return orderRepository.deleteOrder(token, orderId)
    }

    suspend fun fetchOrderByClientNameAndClientLastname(token: String, clientName: String, clientLastname: String): List<Order> {
        return orderRepository.fetchOrderByClientNameAndClientLastname(token, clientName, clientLastname)
    }
}