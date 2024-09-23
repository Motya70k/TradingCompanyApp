package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.AddPurchaseRequest
import com.example.trading_company_client.databinding.CreatePurchaseDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel

class CreatePurchaseDialog(
    private val viewModel: PurchaseViewModel
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = CreatePurchaseDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Добавление заявки на закупку")
            .setPositiveButton("Добавить") { dialog, _ ->
                val itemId = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                val amount = dialogBinding.amountEditText.text.toString().toIntOrNull()
                val cost = dialogBinding.costEditText.text.toString().toIntOrNull()

                if (itemId != null && amount != null && cost != null) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    val addPurchaseRequest = AddPurchaseRequest(0, itemId, amount, cost)
                    viewModel.addPurchase(token.toString(), addPurchaseRequest)
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