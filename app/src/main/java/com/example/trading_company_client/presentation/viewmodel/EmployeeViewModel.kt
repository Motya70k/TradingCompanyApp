package com.example.trading_company_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trading_company_client.data.model.Employee
import com.example.trading_company_client.data.model.requests.RegisterRequest
import com.example.trading_company_client.data.model.requests.UpdateEmployeeRequest
import com.example.trading_company_client.domain.usecase.EmployeeUseCase
import kotlinx.coroutines.launch

class EmployeeViewModel(
    private val employeeUseCase: EmployeeUseCase
): ViewModel() {
    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>> get() = _employees

    private fun loadEmployees(token: String) {
        viewModelScope.launch {
            try {
                val employeeList = employeeUseCase.getAllEmployees(token)
                _employees.postValue(employeeList)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun registerEmployee(token: String, registerRequest: RegisterRequest) {
        viewModelScope.launch {
            try {
                employeeUseCase.registerEmployee(token, registerRequest)
                loadEmployees(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun updateEmployee(token: String, updateEmployeeRequest: UpdateEmployeeRequest) {
        viewModelScope.launch {
            try {
                employeeUseCase.updateEmployee(token, updateEmployeeRequest)
                loadEmployees(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun deleteEmployee(token: String, employeeId: Int) {
        viewModelScope.launch {
            try {
                employeeUseCase.deleteEmployee(token, employeeId)
                loadEmployees(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun searchEmployee(token: String, name: String, lastname: String) {
        viewModelScope.launch {
            try {
                val employees = employeeUseCase.fetchEmployeeByNameAndLastname(token, name, lastname)
                _employees.postValue(employees)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }
}

class EmployeeViewModelFactory(
    private val employeeUseCase: EmployeeUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            return EmployeeViewModel(employeeUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}