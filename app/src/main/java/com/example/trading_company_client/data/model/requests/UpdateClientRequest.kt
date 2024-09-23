package com.example.trading_company_client.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class UpdateClientRequest(
    val id: Int,
    val name: String,
    val lastname: String,
    val phone: String
)
