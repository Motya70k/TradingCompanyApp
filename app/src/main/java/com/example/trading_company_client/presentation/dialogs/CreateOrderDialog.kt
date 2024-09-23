package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.AddOrderRequest
import com.example.trading_company_client.databinding.CreateOrderDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.OrderViewModel

class CreateOrderDialog(
    private val viewModel: OrderViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = CreateOrderDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Добавление заказа")
            .setPositiveButton("Добавить") { dialog, _ ->
                val itemId = dialogBinding.itemIdNameEditText.text.toString().toIntOrNull()
                val clientName = dialogBinding.clientNameEditText.text.toString()
                val clientLastname = dialogBinding.clientLastnameEditText.text.toString()
                val amount = dialogBinding.amountLastnameEditText.text.toString().toIntOrNull()

                if (itemId != null && clientName.isNotBlank() && clientLastname.isNotBlank() && amount != null) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    val addOrderRequest =
                        AddOrderRequest(0, itemId, clientName, clientLastname, amount)
                    viewModel.addOrder(token.toString(), addOrderRequest)
                } else {
                    Toast.makeText(
                        context,
                        "Пожалуйста, заполните все поля корректно",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }
}