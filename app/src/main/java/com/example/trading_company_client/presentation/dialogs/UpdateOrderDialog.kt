package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.UpdateOrderRequest
import com.example.trading_company_client.databinding.UpdateOrderDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.OrderViewModel

class UpdateOrderDialog(
    private val viewModel: OrderViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = UpdateOrderDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Обновление информации о заказах")
            .setPositiveButton("Обновить") { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()
                val itemId = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                val clientName = dialogBinding.clientNameEditText.text.toString()
                val clientLastname = dialogBinding.clientlastnameEditText.text.toString()
                val amount = dialogBinding.amountlastnameEditText.text.toString().toIntOrNull()

                if (id != null && itemId != null && clientName.isNotBlank()
                    && clientLastname.isNotBlank() && amount != null
                ) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    val updatedOrder =
                        UpdateOrderRequest(id, itemId, clientName, clientLastname, amount)
                    viewModel.updateOrder(token.toString(), updatedOrder)
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