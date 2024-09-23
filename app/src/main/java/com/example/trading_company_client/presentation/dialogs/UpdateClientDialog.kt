package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.UpdateClientRequest
import com.example.trading_company_client.databinding.UpdateClientDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ClientViewModel

class UpdateClientDialog(
    private val viewModel: ClientViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = UpdateClientDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Обновление информации о клиентах")
            .setPositiveButton("Обновить") { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()
                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()
                val phone = dialogBinding.phoneEditText.text.toString()

                if (id != null && name.isNotBlank() && lastname.isNotBlank() && phone.isNotBlank()) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    val updatedClient = UpdateClientRequest(id, name, lastname, phone)
                    viewModel.updateClient(token.toString(), updatedClient)
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