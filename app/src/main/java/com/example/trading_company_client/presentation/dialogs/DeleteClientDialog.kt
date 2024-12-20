package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.databinding.DeleteClientDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ClientViewModel

class DeleteClientDialog(
    private val viewModel: ClientViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = DeleteClientDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.delete_client)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                val id = dialogBinding.itemIdEditText.text.toString().toIntOrNull()

                if (id != null) {
                    viewModel.deleteClient(token, id)
                } else {
                    Toast.makeText(
                        context,
                        R.string.please_enter_client_id_correctly,
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