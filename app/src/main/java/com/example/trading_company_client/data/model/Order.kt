package com.example.trading_company_client.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: Int,
    val itemId: Int,
    val itemName: String,
    val clientId: Int,
    val clientName: String,
    val clientLastname: String,
    val amount: Int
)
