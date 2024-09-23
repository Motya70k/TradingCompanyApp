package com.example.trading_company_client.domain.repository

import com.example.trading_company_client.data.model.Item
import com.example.trading_company_client.data.model.requests.AddItemRequest
import com.example.trading_company_client.data.model.requests.UpdateItemRequest
import com.example.trading_company_client.data.model.response.BaseResponse

interface ItemRepository {
    suspend fun getAllItems(token: String): List<Item>

    suspend fun addItem(token: String, addItemRequest: AddItemRequest): BaseResponse

    suspend fun updateItem(token: String, item: UpdateItemRequest): BaseResponse

    suspend fun deleteItem(token: String, itemId: Int): BaseResponse

    suspend fun fetchItemByName(token: String, itemName: String): List<Item>
}