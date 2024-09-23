package com.example.trading_company_client.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trading_company_client.data.model.requests.LoginRequest
import com.example.trading_company_client.data.repository.LoginRequestRepositoryImpl
import com.example.trading_company_client.databinding.ActivityLoginBinding
import com.example.trading_company_client.domain.usecase.LoginRequestUseCase
import com.example.trading_company_client.presentation.viewmodel.LoginViewModel
import com.example.trading_company_client.utils.TokenStorage

class LoginActivity : AppCompatActivity(), TokenStorage {

    private lateinit var loginActivityBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRequestUseCase: LoginRequestUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivityBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginActivityBinding.root)

        loginRequestUseCase = LoginRequestUseCase(LoginRequestRepositoryImpl())
        loginViewModel = LoginViewModel(loginRequestUseCase, this)

        loginViewModel.loginResponse.observe(this) { response ->
            response?.let {
                if (it.success) {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        loginViewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                loginViewModel.clearError()
            }
        }

        loginActivityBinding.buttonAuth.setOnClickListener {
            val login = loginActivityBinding.editLogin.text.toString()
            val password = loginActivityBinding.editPassword.text.toString()
            val loginRequest = LoginRequest(login, password)
            loginViewModel.login(loginRequest)
        }
    }

    override fun saveToken(token: String) {
        val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
        sharedPreferences.edit().putString("token", token).apply()
    }
}