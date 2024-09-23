package com.example.trading_company_client.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePurchaseRequest(
    val id: Int,
    val itemId: Int,
    val amount: Int,
    val cost: Int
)
