package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.databinding.DeleteItemDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ItemViewModel

class DeleteItemDialog(
    private val viewModel: ItemViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = DeleteItemDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Удаление товара")
            .setPositiveButton("Удалить") { dialog, _ ->
                val id = dialogBinding.itemIdEditText.text.toString().toIntOrNull()
                if (id != null) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    viewModel.deleteItem(token.toString(), id)
                } else {
                    Toast.makeText(
                        context,
                        "Пожалуйста, введите корректный ID товара",
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