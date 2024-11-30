package com.example.trading_company_client.presentation.activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trading_company_client.R
import com.example.trading_company_client.data.adapters.ClientRecyclerViewAdapter
import com.example.trading_company_client.data.adapters.EmployeeRecycleViewAdapter
import com.example.trading_company_client.data.adapters.ItemRecyclerViewAdapter
import com.example.trading_company_client.data.adapters.OrderRecyclerViewAdapter
import com.example.trading_company_client.data.adapters.PurchaseRecyclerViewAdapter
import com.example.trading_company_client.databinding.ActivityMainBinding
import com.example.trading_company_client.presentation.dialogs.CreateClientDialog
import com.example.trading_company_client.presentation.dialogs.CreateItemDialog
import com.example.trading_company_client.presentation.dialogs.CreateOrderDialog
import com.example.trading_company_client.presentation.dialogs.CreatePurchaseDialog
import com.example.trading_company_client.presentation.dialogs.DeleteClientDialog
import com.example.trading_company_client.presentation.dialogs.DeleteEmployeeDialog
import com.example.trading_company_client.presentation.dialogs.DeleteItemDialog
import com.example.trading_company_client.presentation.dialogs.DeleteOrderDialog
import com.example.trading_company_client.presentation.dialogs.DeletePurchaseDialog
import com.example.trading_company_client.presentation.dialogs.EntityDialog
import com.example.trading_company_client.presentation.dialogs.RegisterEmployeeDialog
import com.example.trading_company_client.presentation.dialogs.SearchClientDialog
import com.example.trading_company_client.presentation.dialogs.SearchEmployeeDialog
import com.example.trading_company_client.presentation.dialogs.SearchItemDialog
import com.example.trading_company_client.presentation.dialogs.SearchOrderDialog
import com.example.trading_company_client.presentation.dialogs.SearchPurchaseDialog
import com.example.trading_company_client.presentation.dialogs.UpdateClientDialog
import com.example.trading_company_client.presentation.dialogs.UpdateEmployeeDialog
import com.example.trading_company_client.presentation.dialogs.UpdateItemDialog
import com.example.trading_company_client.presentation.dialogs.UpdateOrderDialog
import com.example.trading_company_client.presentation.dialogs.UpdatePurchaseDialog
import com.example.trading_company_client.presentation.viewmodel.ClientViewModel
import com.example.trading_company_client.presentation.viewmodel.EmployeeViewModel
import com.example.trading_company_client.presentation.viewmodel.ItemViewModel
import com.example.trading_company_client.presentation.viewmodel.OrderViewModel
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel
import com.example.trading_company_client.utils.TokenStorage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var employeeAdapter: EmployeeRecycleViewAdapter
    private lateinit var itemAdapter: ItemRecyclerViewAdapter
    private lateinit var purchaseAdapter: PurchaseRecyclerViewAdapter
    private lateinit var clientAdapter: ClientRecyclerViewAdapter
    private lateinit var orderAdapter: OrderRecyclerViewAdapter

    private lateinit var activityBinding: ActivityMainBinding

    private var selectedMenuItem: Int = R.id.employeeItem

    private val employeeViewModel: EmployeeViewModel by viewModels()
    private val clientViewModel: ClientViewModel by viewModels()
    private val itemViewModel: ItemViewModel by viewModels()
    private val orderViewModel: OrderViewModel by viewModels()
    private val purchaseViewModel: PurchaseViewModel by viewModels()

    @Inject
    lateinit var tokenStorage: TokenStorage

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        selectedMenuItem = item.itemId
        initRecyclerViewForSelectedItem()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        val token = tokenStorage.getToken().toString()

        activityBinding.addButton.setOnClickListener {
            showCreateDialog(token)
        }
        activityBinding.updateButton.setOnClickListener {
            showUpdateDialog(token)
        }
        activityBinding.deleteButton.setOnClickListener {
            showDeleteDialog(token)
        }

        activityBinding.searchButton.setOnClickListener {
            showSearchDialog(token)
        }

        observeViewModel()
        initAdapters()
    }

    private fun observeViewModel() {
        employeeViewModel.employees.observe(this) { employees ->
            employeeAdapter.submitList(employees)
        }
        itemViewModel.items.observe(this) { items ->
            itemAdapter.submitList(items)
        }
        purchaseViewModel.purchases.observe(this) { purchases ->
            purchaseAdapter.submitList(purchases)
        }
        clientViewModel.clients.observe(this) { clients ->
            clientAdapter.submitList(clients)
        }
        orderViewModel.orders.observe(this) { orders ->
            orderAdapter.submitList(orders)
        }
    }

    private fun initAdapters() {
        employeeAdapter = EmployeeRecycleViewAdapter()
        itemAdapter = ItemRecyclerViewAdapter()
        purchaseAdapter = PurchaseRecyclerViewAdapter()
        clientAdapter = ClientRecyclerViewAdapter()
        orderAdapter = OrderRecyclerViewAdapter()
    }

    private fun initRecyclerViewForSelectedItem() {
        val adapter = when (selectedMenuItem) {
            R.id.employeeItem -> employeeAdapter
            R.id.itemItem -> itemAdapter
            R.id.purchaseItem -> purchaseAdapter
            R.id.clientItem -> clientAdapter
            R.id.orderItem -> orderAdapter
            else -> null
        }
        adapter?.let { initRecyclerView(it) }
    }

    private fun initRecyclerView(adapter: RecyclerView.Adapter<*>) {
        activityBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        activityBinding.recyclerView.adapter = adapter
    }

    private fun showCreateDialog(token: String) {
        val dialog: EntityDialog? = when (selectedMenuItem) {
            R.id.employeeItem -> RegisterEmployeeDialog(employeeViewModel, token)
            R.id.itemItem -> CreateItemDialog(itemViewModel, token)
            R.id.purchaseItem -> CreatePurchaseDialog(purchaseViewModel, token)
            R.id.clientItem -> CreateClientDialog(clientViewModel, token)
            R.id.orderItem -> CreateOrderDialog(orderViewModel, token)
            else -> null
        }
        dialog?.show(this)
    }

    private fun showUpdateDialog(token: String) {
        val dialog: EntityDialog? = when (selectedMenuItem) {
            R.id.employeeItem -> UpdateEmployeeDialog(employeeViewModel, token)
            R.id.itemItem -> UpdateItemDialog(itemViewModel, token)
            R.id.purchaseItem -> UpdatePurchaseDialog(purchaseViewModel, token)
            R.id.clientItem -> UpdateClientDialog(clientViewModel, token)
            R.id.orderItem -> UpdateOrderDialog(orderViewModel, token)
            else -> null
        }
        dialog?.show(this)
    }

    private fun showDeleteDialog(token: String) {
        val dialog: EntityDialog? = when (selectedMenuItem) {
            R.id.employeeItem -> DeleteEmployeeDialog(employeeViewModel, token)
            R.id.itemItem -> DeleteItemDialog(itemViewModel, token)
            R.id.purchaseItem -> DeletePurchaseDialog(purchaseViewModel, token)
            R.id.clientItem -> DeleteClientDialog(clientViewModel, token)
            R.id.orderItem -> DeleteOrderDialog(orderViewModel, token)
            else -> null
        }
        dialog?.show(this)
    }

    private fun showSearchDialog(token: String) {
        val dialog: EntityDialog? = when (selectedMenuItem) {
            R.id.employeeItem -> SearchEmployeeDialog(employeeViewModel, token)
            R.id.itemItem -> SearchItemDialog(itemViewModel, token)
            R.id.purchaseItem -> SearchPurchaseDialog(purchaseViewModel, token)
            R.id.clientItem -> SearchClientDialog(clientViewModel, token)
            R.id.orderItem -> SearchOrderDialog(orderViewModel, token)
            else -> null
        }
        dialog?.show(this)
    }
}