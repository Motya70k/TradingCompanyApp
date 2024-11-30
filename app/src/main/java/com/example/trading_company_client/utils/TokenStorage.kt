package com.example.trading_company_client.utils

import android.content.SharedPreferences
import javax.inject.Inject

class TokenStorage @Inject constructor(
    val sharedPreferences: SharedPreferences
) {
    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }
}