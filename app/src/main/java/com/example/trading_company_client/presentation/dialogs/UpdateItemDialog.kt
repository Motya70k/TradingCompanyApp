package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.data.model.requests.UpdateItemRequest
import com.example.trading_company_client.databinding.UpdateItemDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ItemViewModel

class UpdateItemDialog(
    private val viewModel: ItemViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = UpdateItemDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Обновление информации о товаре")
            .setPositiveButton("Обновить") { dialog, _ ->
                val id = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                val name = dialogBinding.itemNameEditText.text.toString()
                val quantity = dialogBinding.quantityEditText.text.toString().toIntOrNull()

                if (id != null && name.isNotBlank() && quantity != null) {
                    val updatedItem = UpdateItemRequest(id, name, quantity)
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")

                    viewModel.updateItem(token.toString(), updatedItem)
                } else {
                    Toast.makeText(context,"Пожалуйста, заполните все поля корректно", Toast.LENGTH_SHORT).show()
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