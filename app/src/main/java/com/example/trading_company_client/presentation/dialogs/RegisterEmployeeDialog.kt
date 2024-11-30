package com.example.trading_company_client.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.RegisterRequest
import com.example.trading_company_client.databinding.RegisterEmployeeDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.EmployeeViewModel

class RegisterEmployeeDialog(
    private val viewModel: EmployeeViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = RegisterEmployeeDialogLayoutBinding.inflate(LayoutInflater.from(context))

        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.add_employee)
            .setPositiveButton(R.string.add) { dialog, _ ->
                val login = dialogBinding.loginEditText.text.toString()
                val password = dialogBinding.passwordEditText.text.toString()
                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()
                val phone = dialogBinding.phoneEditText.text.toString()
                val role = dialogBinding.roleEditText.text.toString()

                if (login.isNotBlank() && password.isNotBlank() && name.isNotBlank() &&
                    lastname.isNotBlank() && phone.isNotBlank() && role.isNotBlank()) {

                    val registerRequest = RegisterRequest(login, password, name, lastname, phone, role)
                    viewModel.registerEmployee(token, registerRequest)
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