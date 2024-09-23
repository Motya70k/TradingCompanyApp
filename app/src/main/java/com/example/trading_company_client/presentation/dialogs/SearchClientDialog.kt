package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.trading_company_client.databinding.SearchClientDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.ClientViewModel

class SearchClientDialog(
    private val viewModel: ClientViewModel
): EntityDialog {

    override fun show(context: Context) {
        val dialogBinding = SearchClientDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle("Поиск клиента")
            .setPositiveButton("Найти") { dialog, _ ->
                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()

                if (name.isNotBlank() && lastname.isNotBlank()) {
                    val sharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
                    val token = sharedPreferences.getString("token", "")
                    viewModel.searchClient(token.toString(), name, lastname)
                } else {
                    Toast.makeText(
                        context,
                        "Пожалуйста, проверьте поля ввода",
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