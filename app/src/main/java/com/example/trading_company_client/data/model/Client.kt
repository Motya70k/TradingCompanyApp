package com.example.trading_company_client.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: Int,
    val name: String,
    val lastname: String,
    val phone: String
)
