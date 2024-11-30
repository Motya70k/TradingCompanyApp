package com.example.trading_company_client.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.UpdateEmployeeRequest
import com.example.trading_company_client.databinding.UpdateEmployeeDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.EmployeeViewModel

class UpdateEmployeeDialog(
    private val viewModel: EmployeeViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = UpdateEmployeeDialogLayoutBinding.inflate(LayoutInflater.from(context))

        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.update_information_about_employee)
            .setPositiveButton(R.string.update) { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()
                val login = dialogBinding.loginEditText.text.toString()
                val password = dialogBinding.passwordEditText.text.toString()
                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()
                val phone = dialogBinding.phoneEditText.text.toString()
                val role = dialogBinding.roleEditText.text.toString()

                if (id != null && login.isNotBlank() && password.isNotBlank() && name.isNotBlank()
                    && lastname.isNotBlank() && phone.isNotBlank() && role.isNotBlank()) {
                    val updatedEmployee = UpdateEmployeeRequest(id, login, password, name, lastname, phone, role)
                    viewModel.updateEmployee(token, updatedEmployee)
                } else {
                    Toast.makeText(context, R.string.please_fill_all_fields_correctly, Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}