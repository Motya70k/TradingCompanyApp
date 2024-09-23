package com.example.trading_company_client.domain.repository

import com.example.trading_company_client.data.model.Item
import com.example.trading_company_client.data.model.Purchase
import com.example.trading_company_client.data.model.requests.AddItemRequest
import com.example.trading_company_client.data.model.requests.AddPurchaseRequest
import com.example.trading_company_client.data.model.requests.UpdateItemRequest
import com.example.trading_company_client.data.model.requests.UpdatePurchaseRequest
import com.example.trading_company_client.data.model.response.BaseResponse

interface PurchaseRepository {

    suspend fun getAllPurchase(token: String): List<Purchase>

    suspend fun addPurchase(token: String, addPurchaseRequest: AddPurchaseRequest): BaseResponse

    suspend fun updatePurchase(
        token: String,
        updatePurchaseRequest: UpdatePurchaseRequest
    ): BaseResponse

    suspend fun deletePurchase(token: String, purchaseId: Int): BaseResponse

    suspend fun fetchPurchaseByItemName(token: String, itemName: String): List<Purchase>
}