package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.AddClientRequest
import com.example.trading_company_client.databinding.CreateClientDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ClientViewModel

class CreateClientDialog(
    private val viewModel: ClientViewModel
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = CreateClientDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Добавление клиента")
            .setPositiveButton("Добавить") { dialog, _ ->
                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()
                val phone = dialogBinding.phoneEditText.text.toString()

                if (name.isNotBlank() && lastname.isNotBlank() && phone.isNotBlank()) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    val addClientRequest = AddClientRequest(0, name, lastname, phone)
                    viewModel.addClient(token.toString(), addClientRequest)
                } else {
                    Toast.makeText(
                        context,
                        "Пожалуйста, заполните все поля корректно",
                        Toast.LENGTH_SHORT
                    ).show()
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