package com.example.trading_company_client.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.UpdateEmployeeRequest
import com.example.trading_company_client.databinding.UpdateEmployeeDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.EmployeeViewModel

class UpdateEmployeeDialog(
    private val viewModel: EmployeeViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = UpdateEmployeeDialogLayoutBinding.inflate(LayoutInflater.from(context))

        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Обновление информации о работнике")
            .setPositiveButton("Обновить") { dialog, _ ->
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
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    viewModel.updateEmployee(token.toString(), updatedEmployee)
                } else {
                    Toast.makeText(context, "Пожалуйста, заполните все поля корректно", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}