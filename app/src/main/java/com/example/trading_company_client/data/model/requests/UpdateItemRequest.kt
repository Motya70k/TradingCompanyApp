package com.example.trading_company_client.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class UpdateItemRequest(
    val id: Int,
    val name: String,
    val quantity: Int
)
