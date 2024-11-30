package com.example.trading_company_client.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.example.trading_company_client.R
import com.example.trading_company_client.databinding.DeletePurchaseDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel

class DeletePurchaseDialog(
    private val viewModel: PurchaseViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = DeletePurchaseDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.delete_purchase)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                val id = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                if (id != null) {
                    viewModel.deletePurchase(token, id)
                } else {
                    Toast.makeText(
                        context,
                        R.string.please_enter_purchase_id_correctly,
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