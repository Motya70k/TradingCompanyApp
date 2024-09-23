package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.AddItemRequest
import com.example.trading_company_client.databinding.CreateItemDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ItemViewModel

class CreateItemDialog(
    private val viewModel: ItemViewModel
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = CreateItemDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Добавление товара")
            .setPositiveButton("Добавить") { dialog, _ ->
                val name = dialogBinding.itemNameEditText.text.toString()
                val quantity = dialogBinding.quantityEditText.text.toString().toIntOrNull()

                if (name.isNotBlank() && quantity != null) {
                    val addItemRequest = AddItemRequest(0, name, quantity)
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")

                    viewModel.addItem(token.toString(), addItemRequest)
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