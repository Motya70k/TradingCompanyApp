package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.UpdateOrderRequest
import com.example.trading_company_client.databinding.UpdateOrderDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.OrderViewModel

class UpdateOrderDialog(
    private val viewModel: OrderViewModel,
    private val token: String
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = UpdateOrderDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.update_information_about_order)
            .setPositiveButton(R.string.update) { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()
                val itemId = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                val clientName = dialogBinding.clientNameEditText.text.toString()
                val clientLastname = dialogBinding.clientlastnameEditText.text.toString()
                val amount = dialogBinding.amountlastnameEditText.text.toString().toIntOrNull()

                if (id != null && itemId != null && clientName.isNotBlank()
                    && clientLastname.isNotBlank() && amount != null
                ) {
                    val updatedOrder = UpdateOrderRequest(id, itemId, clientName, clientLastname, amount)
                    viewModel.updateOrder(token, updatedOrder)
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