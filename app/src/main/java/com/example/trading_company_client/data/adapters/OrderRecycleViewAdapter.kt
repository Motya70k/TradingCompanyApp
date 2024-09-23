package com.example.trading_company_client.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.Order
import com.example.trading_company_client.databinding.OrderItemBinding

class OrderRecyclerViewAdapter : ListAdapter<Order, OrderRecyclerViewAdapter.TableHolder>(DiffCallback()) {

    inner class TableHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = OrderItemBinding.bind(item)

        fun bind(order: Order) {
            with(binding) {
                idTextView.setText("id: ${order.id}")
                itemIdTextView.setText("id товара: ${order.itemId}")
                itemNameTextView.setText("Название товара: ${order.itemName}")
                clientIdTextView.setText("id клиента: ${order.clientId}")
                clientNameTextView.setText("Имя клиента: ${order.clientName}")
                clientLastnameTextView.setText("Фамилия клиента: ${order.clientLastname}")
                amountTextView.setText("Количество: ${order.amount}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return TableHolder(view)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }
}