package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.AddItemRequest
import com.example.trading_company_client.databinding.CreateItemDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ItemViewModel

class CreateItemDialog(
    private val viewModel: ItemViewModel,
    private val token: String
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = CreateItemDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.add_item)
            .setPositiveButton(R.string.add) { dialog, _ ->
                val name = dialogBinding.itemNameEditText.text.toString()
                val quantity = dialogBinding.quantityEditText.text.toString().toIntOrNull()

                if (name.isNotBlank() && quantity != null) {
                    val addItemRequest = AddItemRequest(0, name, quantity)
                    viewModel.addItem(token, addItemRequest)
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