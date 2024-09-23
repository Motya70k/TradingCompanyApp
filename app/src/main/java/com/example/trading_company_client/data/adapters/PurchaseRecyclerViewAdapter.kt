package com.example.trading_company_client.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trading_company_client.R
import com.example.trading_company_client.data.model.Purchase
import com.example.trading_company_client.databinding.PurchaseItemBinding

class PurchaseRecyclerViewAdapter : ListAdapter<Purchase, PurchaseRecyclerViewAdapter.TableHolder>(DiffCallback()) {

    inner class TableHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = PurchaseItemBinding.bind(item)

        fun bind(purchase: Purchase) {
            with(binding) {
                idTextView.setText("id: ${purchase.id}")
                itemIdTextView.setText("id товара: ${purchase.itemId}")
                nameTextView.setText("Название товара: ${purchase.name}")
                amountTextView.setText("Количество: ${purchase.amount}")
                costTextView.setText("Стоимость: ${purchase.cost}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.purchase_item, parent, false)
        return TableHolder(view)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Purchase>() {
        override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
            return oldItem == newItem
        }
    }
}