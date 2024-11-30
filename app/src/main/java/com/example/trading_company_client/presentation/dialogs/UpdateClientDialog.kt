package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.UpdateClientRequest
import com.example.trading_company_client.databinding.UpdateClientDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ClientViewModel

class UpdateClientDialog(
    private val viewModel: ClientViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = UpdateClientDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.update_information_about_client)
            .setPositiveButton(R.string.update) { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()
                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()
                val phone = dialogBinding.phoneEditText.text.toString()

                if (id != null && name.isNotBlank() && lastname.isNotBlank() && phone.isNotBlank()) {
                    val updatedClient = UpdateClientRequest(id, name, lastname, phone)
                    viewModel.updateClient(token, updatedClient)
                } else {
                    Toast.makeText(
                        context,
                        R.string.please_fill_all_fields_correctly,
                        Toast.LENGTH_SHORT
                    ).show()
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