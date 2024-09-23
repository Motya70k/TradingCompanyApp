package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.UpdatePurchaseRequest
import com.example.trading_company_client.databinding.UpdatePurchaseDialogItemBinding
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel

class UpdatePurchaseDialog(
    private val viewModel: PurchaseViewModel
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = UpdatePurchaseDialogItemBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Добавление заявки на закупку")
            .setPositiveButton("Добавить") { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()
                val itemIdName = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                val amount = dialogBinding.amountEditText.text.toString().toIntOrNull()
                val cost = dialogBinding.costEditText.text.toString().toIntOrNull()

                if (id != null && itemIdName != null && amount != null && cost != null) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    val updatedPurchase = UpdatePurchaseRequest(id, itemIdName, amount, cost)
                    viewModel.updatePurchase(token.toString(), updatedPurchase)
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