package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.AddClientRequest
import com.example.trading_company_client.databinding.CreateClientDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ClientViewModel

class CreateClientDialog(
    private val viewModel: ClientViewModel,
    private val token: String
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = CreateClientDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.add_client)
            .setPositiveButton(R.string.add) { dialog, _ ->
                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()
                val phone = dialogBinding.phoneEditText.text.toString()

                if (name.isNotBlank() && lastname.isNotBlank() && phone.isNotBlank()) {
                    val addClientRequest = AddClientRequest(0, name, lastname, phone)
                    viewModel.addClient(token, addClientRequest)
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