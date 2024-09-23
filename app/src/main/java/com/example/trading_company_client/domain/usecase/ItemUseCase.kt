package com.example.trading_company_client.domain.usecase

import com.example.trading_company_client.data.model.Item
import com.example.trading_company_client.data.model.requests.AddItemRequest
import com.example.trading_company_client.data.model.requests.UpdateItemRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.domain.repository.ItemRepository

class ItemUseCase(
    private val itemRepository: ItemRepository
) {
    suspend fun getAllItems(token: String): List<Item> {
        return itemRepository.getAllItems(token)
    }

    suspend fun addItem(token: String, addItemRequest: AddItemRequest) {
        itemRepository.addItem(token, addItemRequest)
    }

    suspend fun updateItem(token: String, item: UpdateItemRequest) {
        itemRepository.updateItem(token, item)
    }

    suspend fun deleteItem(token: String, itemId: Int) {
        itemRepository.deleteItem(token, itemId)
    }

    suspend fun fetchItemByName(token: String, itemName: String): List<Item> {
        return itemRepository.fetchItemByName(token, itemName)
    }
}