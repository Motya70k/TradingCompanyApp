package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.databinding.SearchItemDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ItemViewModel

class SearchItemDialog(
    private val viewModel: ItemViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = SearchItemDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.search_item)
            .setPositiveButton(R.string.search) { dialog, _ ->
                val itemName = dialogBinding.itemNameEditText.text.toString()
                if (itemName.isNotBlank()) {
                    viewModel.searchItem(token, itemName)
                } else {
                    Toast.makeText(
                        context,
                        R.string.please_enter_item_name,
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