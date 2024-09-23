package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.databinding.SearchOrderDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.OrderViewModel

class SearchOrderDialog(
    private val viewModel: OrderViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = SearchOrderDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Поиск заказа")
            .setPositiveButton("Найти") { dialog, _ ->
                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()

                if (name.isNotBlank() && lastname.isNotBlank()) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    viewModel.searchOrder(token.toString(), name, lastname)
                } else {
                    Toast.makeText(
                        context,
                        "Пожалуйста, введите корректное имя и фамилию клиента",
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