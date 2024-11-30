package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.UpdateItemRequest
import com.example.trading_company_client.databinding.UpdateItemDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ItemViewModel

class UpdateItemDialog(
    private val viewModel: ItemViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = UpdateItemDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.update_information_about_item)
            .setPositiveButton(R.string.update) { dialog, _ ->
                val id = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                val name = dialogBinding.itemNameEditText.text.toString()
                val quantity = dialogBinding.quantityEditText.text.toString().toIntOrNull()

                if (id != null && name.isNotBlank() && quantity != null) {
                    val updatedItem = UpdateItemRequest(id, name, quantity)
                    viewModel.updateItem(token, updatedItem)
                } else {
                    Toast.makeText(context,R.string.please_fill_all_fields_correctly, Toast.LENGTH_SHORT).show()
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