package com.example.trading_company_client.domain.usecase

import com.example.trading_company_client.data.model.Employee
import com.example.trading_company_client.data.model.requests.RegisterRequest
import com.example.trading_company_client.data.model.requests.UpdateEmployeeRequest
import com.example.trading_company_client.data.model.response.BaseResponse
import com.example.trading_company_client.domain.repository.EmployeeRepository

class EmployeeUseCase (
    private val employeeRepository: EmployeeRepository
) {
    suspend fun getAllEmployees(token: String): List<Employee> {
        return employeeRepository.getAllEmployees(token)
    }

    suspend fun registerEmployee(token: String, registerRequest: RegisterRequest): BaseResponse {
        return employeeRepository.registerEmployee(token, registerRequest)
    }

    suspend fun updateEmployee(
        token: String,
        updateEmployeeRequest: UpdateEmployeeRequest
    ): BaseResponse {
        return employeeRepository.updateEmployee(token, updateEmployeeRequest)
    }

    suspend fun deleteEmployee(token: String, employeeId: Int): BaseResponse {
        return employeeRepository.deleteEmployee(token, employeeId)
    }

    suspend fun fetchEmployeeByNameAndLastname(token: String, name:String, lastname: String): List<Employee> {
        return employeeRepository.fetchEmployeeByNameAndLastname(token, name, lastname)
    }
}