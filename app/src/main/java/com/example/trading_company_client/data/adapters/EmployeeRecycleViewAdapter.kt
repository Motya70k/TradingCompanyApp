package com.example.trading_company_client.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.Employee
import com.example.trading_company_client.databinding.EmployeeItemBinding
class EmployeeRecycleViewAdapter : ListAdapter<Employee, EmployeeRecycleViewAdapter.TableHolder>(
    DiffCallback()
) {

    class TableHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = EmployeeItemBinding.bind(item)

        fun bind(employee: Employee) = with(binding) {
            idTextView.text = idTextView.text.toString() + employee.id.toString()
            loginTextView.text = loginTextView.text.toString() + employee.login
            passwordTextView.text = passwordTextView.text.toString() + employee.password
            nameTextView.text = nameTextView.text.toString() + employee.name
            lastnameTextView.text = lastnameTextView.text.toString() + employee.lastname
            phoneTextView.text = phoneTextView.text.toString() + employee.phoneNumber
            roleTextView.text = roleTextView.text.toString() + employee.role
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_item, parent, false)
        return TableHolder(view)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }
}