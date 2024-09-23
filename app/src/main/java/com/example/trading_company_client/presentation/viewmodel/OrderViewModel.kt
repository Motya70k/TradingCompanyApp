package com.example.trading_company_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trading_company_client.data.model.Order
import com.example.trading_company_client.data.model.requests.AddOrderRequest
import com.example.trading_company_client.data.model.requests.UpdateOrderRequest
import com.example.trading_company_client.domain.usecase.OrderUseCase
import kotlinx.coroutines.launch

class OrderViewModel(
    private val orderUseCase: OrderUseCase
): ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> get() = _orders

    private fun loadOrders(token: String) {
        viewModelScope.launch {
            try {
                val orderList = orderUseCase.getAllOrders(token)
                _orders.postValue(orderList)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun addOrder(token: String, addOrderRequest: AddOrderRequest) {
        viewModelScope.launch {
            try {
                orderUseCase.addOrder(token, addOrderRequest)
                loadOrders(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun updateOrder(token: String, updateOrderRequest: UpdateOrderRequest) {
        viewModelScope.launch {
            try {
                orderUseCase.updateOrder(token, updateOrderRequest)
                loadOrders(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun deleteOrder(token: String, orderId: Int) {
        viewModelScope.launch {
            try {
                orderUseCase.deleteOrder(token, orderId)
                loadOrders(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun searchOrder(token: String, clientName: String, clientLastname: String) {
        viewModelScope.launch {
            try {
                val orders = orderUseCase.fetchOrderByClientNameAndClientLastname(token, clientName, clientLastname)
                _orders.postValue(orders)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }
}

class OrderViewModelFactory(
    private val orderUseCase: OrderUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            return OrderViewModel(orderUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}