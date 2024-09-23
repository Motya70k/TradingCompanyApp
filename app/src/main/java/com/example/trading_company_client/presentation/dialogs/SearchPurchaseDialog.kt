package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.databinding.SearchPurchaseDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel

class SearchPurchaseDialog(
    private val viewModel: PurchaseViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = SearchPurchaseDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Поиск закупки")
            .setPositiveButton("Найти") { dialog, _ ->
                val itemName = dialogBinding.itemNameEditText.text.toString()
                if (itemName.isNotBlank()) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    viewModel.searchPurchase(token.toString(), itemName)
                } else {
                    Toast.makeText(
                        context,
                        "Ошибка при поиске заявки",
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