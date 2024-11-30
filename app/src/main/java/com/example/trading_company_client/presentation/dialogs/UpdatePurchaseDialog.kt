package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.UpdatePurchaseRequest
import com.example.trading_company_client.databinding.UpdatePurchaseDialogItemBinding
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel

class UpdatePurchaseDialog(
    private val viewModel: PurchaseViewModel,
    private val token: String
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = UpdatePurchaseDialogItemBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.update_information_about_purchase)
            .setPositiveButton(R.string.update) { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()
                val itemIdName = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                val amount = dialogBinding.amountEditText.text.toString().toIntOrNull()
                val cost = dialogBinding.costEditText.text.toString().toIntOrNull()

                if (id != null && itemIdName != null && amount != null && cost != null) {
                    val updatedPurchase = UpdatePurchaseRequest(id, itemIdName, amount, cost)
                    viewModel.updatePurchase(token, updatedPurchase)
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