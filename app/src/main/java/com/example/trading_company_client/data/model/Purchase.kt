package com.example.trading_company_client.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Purchase(
    val id: Int,
    val itemId: Int,
    val name: String,
    val amount: Int,
    val cost: Int
)
