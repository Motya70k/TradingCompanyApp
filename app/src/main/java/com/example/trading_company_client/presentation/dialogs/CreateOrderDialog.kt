package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.requests.AddOrderRequest
import com.example.trading_company_client.databinding.CreateOrderDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.OrderViewModel

class CreateOrderDialog(
    private val viewModel: OrderViewModel,
    private val token: String
) : EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = CreateOrderDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.add_order)
            .setPositiveButton(R.string.add) { dialog, _ ->
                val itemId = dialogBinding.itemIdNameEditText.text.toString().toIntOrNull()
                val clientName = dialogBinding.clientNameEditText.text.toString()
                val clientLastname = dialogBinding.clientLastnameEditText.text.toString()
                val amount = dialogBinding.amountLastnameEditText.text.toString().toIntOrNull()

                if (itemId != null && clientName.isNotBlank() && clientLastname.isNotBlank() && amount != null) {
                    val addOrderRequest = AddOrderRequest(0, itemId, clientName, clientLastname, amount)
                    viewModel.addOrder(token, addOrderRequest)
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