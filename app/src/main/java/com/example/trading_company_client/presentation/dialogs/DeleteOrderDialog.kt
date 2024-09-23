package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.databinding.DeleteOrderDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.OrderViewModel

class DeleteOrderDialog(
    private val viewModel: OrderViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = DeleteOrderDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Удаление заказа")
            .setPositiveButton("Удалить") { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()

                if (id != null) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    viewModel.deleteOrder(token.toString(), id)
                } else {
                    Toast.makeText(
                        context,
                        "Пожалуйста, введите корректный ID заказа",
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