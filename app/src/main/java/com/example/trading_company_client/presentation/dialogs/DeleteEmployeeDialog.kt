package com.example.trading_company_client.presentation.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.trading_company_client.R
import com.example.trading_company_client.databinding.DeleteEmployeeDialogLayoutBinding
import com.example.trading_company_client.presentation.viewmodel.EmployeeViewModel

class DeleteEmployeeDialog(
    private val viewModel: EmployeeViewModel,
    private val token: String
): EntityDialog {
    override fun show(context: Context) {
        val dialogBinding =
            DeleteEmployeeDialogLayoutBinding.inflate(LayoutInflater.from(context))

        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setTitle(R.string.delete_employee)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                val id = dialogBinding.idEditText.text.toString().toIntOrNull()

                if (id != null) {
                    viewModel.deleteEmployee(token, id)
                } else {
                    Toast.makeText(
                        context,
                        R.string.please_enter_employee_id_correctly,
                        Toast.LENGTH_SHORT
                    ).show()
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