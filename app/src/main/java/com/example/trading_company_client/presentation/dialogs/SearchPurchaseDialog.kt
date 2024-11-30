package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.databinding.SearchPurchaseDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel

class SearchPurchaseDialog(
    private val viewModel: PurchaseViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = SearchPurchaseDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.search_purchase)
            .setPositiveButton(R.string.search) { dialog, _ ->
                val itemName = dialogBinding.itemNameEditText.text.toString()
                if (itemName.isNotBlank()) {
                    viewModel.searchPurchase(token, itemName)
                } else {
                    Toast.makeText(
                        context,
                        R.string.error_while_searching_for_purhcase,
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