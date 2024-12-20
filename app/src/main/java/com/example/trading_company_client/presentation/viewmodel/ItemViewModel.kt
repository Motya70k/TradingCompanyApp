package com.example.trading_company_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trading_company_client.data.model.Item
import com.example.trading_company_client.data.model.requests.AddItemRequest
import com.example.trading_company_client.data.model.requests.UpdateItemRequest
import com.example.trading_company_client.domain.usecase.ItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemUseCase: ItemUseCase
) : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    private fun loadItems(token: String) {
        viewModelScope.launch {
            try {
                val itemList = itemUseCase.getAllItems(token)
                _items.postValue(itemList)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun addItem(token: String, addItemRequest: AddItemRequest) {
        viewModelScope.launch {
            try {
                itemUseCase.addItem(token, addItemRequest)
                loadItems(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun updateItem(token: String, updateItemRequest: UpdateItemRequest) {
        viewModelScope.launch {
            try {
                itemUseCase.updateItem(token, updateItemRequest)
                loadItems(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun deleteItem(token: String, itemId: Int) {
        viewModelScope.launch {
            try {
                itemUseCase.deleteItem(token, itemId)
                loadItems(token)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    fun searchItem(token: String, itemName: String) {
        viewModelScope.launch {
            try {
                val items = itemUseCase.fetchItemByName(token, itemName)
                _items.postValue(items)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }
}