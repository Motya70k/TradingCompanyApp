package com.example.trading_company_client.domain.usecase

import com.example.trading_company_client.data.model.Purchase
import com.example.trading_company_client.data.model.requests.AddPurchaseRequest
import com.example.trading_company_client.data.model.requests.UpdatePurchaseRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.domain.repository.PurchaseRepository

class PurchaseUseCase (
    private val purchaseRepository: PurchaseRepository
) {

    suspend fun getAllPurchase(token: String): List<Purchase> {
        return purchaseRepository.getAllPurchase(token)
    }

    suspend fun addPurchase(token: String, addPurchaseRequest: AddPurchaseRequest): BaseResponse {
        return purchaseRepository.addPurchase(token, addPurchaseRequest)
    }

    suspend fun updatePurchase(token: String, updatePurchaseRequest: UpdatePurchaseRequest): BaseResponse {
        return purchaseRepository.updatePurchase(token, updatePurchaseRequest)
    }

    suspend fun deletePurchase(token: String, purchaseId: Int): BaseResponse {
        return purchaseRepository.deletePurchase(token, purchaseId)
    }

    suspend fun fetchPurchaseByItemName(token: String, itemName: String): List<Purchase> {
        return purchaseRepository.fetchPurchaseByItemName(token, itemName)
    }
}