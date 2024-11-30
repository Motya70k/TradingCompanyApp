package com.example.trading_company_client.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.trading_company_client.data.model.requests.LoginRequest
import com.example.trading_company_client.databinding.ActivityLoginBinding
import com.example.trading_company_client.presentation.viewmodel.LoginViewModel
import com.example.trading_company_client.utils.TokenStorage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var loginActivityBinding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivityBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginActivityBinding.root)

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
}