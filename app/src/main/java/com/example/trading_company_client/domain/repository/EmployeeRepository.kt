package com.example.trading_company_client.domain.repository

import com.example.trading_company_client.data.model.Employee
import com.example.trading_company_client.data.model.requests.RegisterRequest
import com.example.trading_company_client.data.model.requests.UpdateEmployeeRequest
import com.example.trading_company_client.data.model.response.BaseResponse

interface EmployeeRepository {

    suspend fun getAllEmployees(token: String): List<Employee>

    suspend fun registerEmployee(token: String, registerRequest: RegisterRequest): BaseResponse

    suspend fun updateEmployee(
        token: String,
        updateEmployeeRequest: UpdateEmployeeRequest
    ): BaseResponse

    suspend fun deleteEmployee(token: String, employeeId: Int): BaseResponse

    suspend fun fetchEmployeeByNameAndLastname(token: String, name:String, lastname: String): List<Employee>
}