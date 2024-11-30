package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.databinding.SearchEmployeeDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.EmployeeViewModel

class SearchEmployeeDialog(
    private val viewModel: EmployeeViewModel,
    private val token: String
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding = SearchEmployeeDialogLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.search_employee)
            .setPositiveButton(R.string.search) { dialog, _ ->

                val name = dialogBinding.nameEditText.text.toString()
                val lastname = dialogBinding.lastnameEditText.text.toString()

                if (name.isNotBlank() && lastname.isNotBlank()) {
                    viewModel.searchEmployee(token, name, lastname)
                } else {
                    Toast.makeText(context, R.string.please_enter_name_and_lastname, Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }
}