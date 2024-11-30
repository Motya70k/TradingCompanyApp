package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.AddPurchaseRequest
import com.example.trading_company_client.databinding.CreatePurchaseDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel

class CreatePurchaseDialog(
    private val viewModel: PurchaseViewModel,
    private val token: String
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = CreatePurchaseDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.add_purchase)
            .setPositiveButton(R.string.add) { dialog, _ ->
                val itemId = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                val amount = dialogBinding.amountEditText.text.toString().toIntOrNull()
                val cost = dialogBinding.costEditText.text.toString().toIntOrNull()

                if (itemId != null && amount != null && cost != null) {
                    val addPurchaseRequest = AddPurchaseRequest(0, itemId, amount, cost)
                    viewModel.addPurchase(token, addPurchaseRequest)
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