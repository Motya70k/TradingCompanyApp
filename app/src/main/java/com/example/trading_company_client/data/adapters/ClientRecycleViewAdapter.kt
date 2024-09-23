package com.example.trading_company_client.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.Client
import com.example.trading_company_client.data.model.Order
import com.example.trading_company_client.databinding.ClientItemBinding

class ClientRecyclerViewAdapter : ListAdapter<Client, ClientRecyclerViewAdapter.TableHolder>(DiffCallback()) {

    inner class TableHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = ClientItemBinding.bind(item)

        fun bind(client:Client) {
            with(binding) {
                idTextView.setText("id: ${client.id}")
                clientNameTextView.setText("Имя клиента: ${client.name}")
                clientSurnameTextView.setText("Фамилия: ${client.lastname}")
                phoneTextView.setText("Номер телефона: ${client.phone}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.client_item, parent, false)
        return TableHolder(view)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Client>() {
        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem == newItem
        }
    }
}