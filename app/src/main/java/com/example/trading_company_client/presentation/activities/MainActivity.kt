package com.example.trading_company_client.presentation.activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trading_company_client.R
import com.example.trading_company_client.data.adapters.ClientRecyclerViewAdapter
import com.example.trading_company_client.data.adapters.EmployeeRecycleViewAdapter
import com.example.trading_company_client.data.adapters.ItemRecyclerViewAdapter
import com.example.trading_company_client.data.adapters.OrderRecyclerViewAdapter
import com.example.trading_company_client.data.adapters.PurchaseRecyclerViewAdapter
import com.example.trading_company_client.data.repository.ClientRepositoryImpl
import com.example.trading_company_client.data.repository.EmployeeRepositoryImpl
import com.example.trading_company_client.data.repository.ItemRepositoryImpl
import com.example.trading_company_client.data.repository.OrderRepositoryImpl
import com.example.trading_company_client.data.repository.PurchaseRepositoryImpl
import com.example.trading_company_client.databinding.ActivityMainBinding
import com.example.trading_company_client.domain.usecase.ClientUseCase
import com.example.trading_company_client.domain.usecase.EmployeeUseCase
import com.example.trading_company_client.domain.usecase.ItemUseCase
import com.example.trading_company_client.domain.usecase.OrderUseCase
import com.example.trading_company_client.domain.usecase.PurchaseUseCase
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
import com.example.trading_company_client.presentation.viewmodel.ClientViewModelFactory
import com.example.trading_company_client.presentation.viewmodel.EmployeeViewModel
import com.example.trading_company_client.presentation.viewmodel.EmployeeViewModelFactory
import com.example.trading_company_client.presentation.viewmodel.ItemViewModel
import com.example.trading_company_client.presentation.viewmodel.ItemViewModelFactory
import com.example.trading_company_client.presentation.viewmodel.OrderViewModel
import com.example.trading_company_client.presentation.viewmodel.OrderViewModelFactory
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModel
import com.example.trading_company_client.presentation.viewmodel.PurchaseViewModelFactory
class MainActivity : AppCompatActivity() {

    private lateinit var employeeAdapter: EmployeeRecycleViewAdapter
    private lateinit var itemAdapter: ItemRecyclerViewAdapter
    private lateinit var purchaseAdapter: PurchaseRecyclerViewAdapter
    private lateinit var clientAdapter: ClientRecyclerViewAdapter
    private lateinit var orderAdapter: OrderRecyclerViewAdapter

    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var purchaseViewModel: PurchaseViewModel
    private lateinit var clientViewModel: ClientViewModel
    private lateinit var orderViewModel: OrderViewModel

    private lateinit var activityBinding: ActivityMainBinding

    private var selectedMenuItem: Int = R.id.employeeItem

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

        activityBinding.addButton.setOnClickListener {
            showCreateDialog()
        }
        activityBinding.updateButton.setOnClickListener {
            showUpdateDialog()
        }
        activityBinding.deleteButton.setOnClickListener {
            showDeleteDialog()
        }

        activityBinding.searchButton.setOnClickListener {
            showSearchDialog()
        }

        initViewModels()
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

    private fun initViewModels() {
        val employeeRepository = EmployeeRepositoryImpl()
        val employeeUseCase = EmployeeUseCase(employeeRepository)
        val employeeViewModelFactory = EmployeeViewModelFactory(employeeUseCase)

        val itemRepository = ItemRepositoryImpl()
        val itemUseCase = ItemUseCase(itemRepository)
        val itemViewModelFactory = ItemViewModelFactory(itemUseCase)

        val clientRepository = ClientRepositoryImpl()
        val clientUseCase = ClientUseCase(clientRepository)
        val clientViewModelFactory = ClientViewModelFactory(clientUseCase)

        val orderRepository = OrderRepositoryImpl()
        val orderUseCase = OrderUseCase(orderRepository)
        val orderViewModelFactory = OrderViewModelFactory(orderUseCase)

        val purchaseRepository = PurchaseRepositoryImpl()
        val purchaseUseCase = PurchaseUseCase(purchaseRepository)
        val purchaseViewModelFactory = PurchaseViewModelFactory(purchaseUseCase)

        employeeViewModel = ViewModelProvider(this, employeeViewModelFactory)[EmployeeViewModel::class.java]
        itemViewModel = ViewModelProvider(this, itemViewModelFactory)[ItemViewModel::class.java]
        purchaseViewModel = ViewModelProvider(this, purchaseViewModelFactory)[PurchaseViewModel::class.java]
        clientViewModel = ViewModelProvider(this, clientViewModelFactory)[ClientViewModel::class.java]
        orderViewModel = ViewModelProvider(this, orderViewModelFactory)[OrderViewModel::class.java]
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

    private fun showCreateDialog() {
        val dialog: EntityDialog? = when (selectedMenuItem) {
            R.id.employeeItem -> RegisterEmployeeDialog(employeeViewModel)
            R.id.itemItem -> CreateItemDialog(itemViewModel)
            R.id.purchaseItem -> CreatePurchaseDialog(purchaseViewModel)
            R.id.clientItem -> CreateClientDialog(clientViewModel)
            R.id.orderItem -> CreateOrderDialog(orderViewModel)
            else -> null
        }
        dialog?.show(this)
    }

    private fun showUpdateDialog() {
        val dialog: EntityDialog? = when (selectedMenuItem) {
            R.id.employeeItem -> UpdateEmployeeDialog(employeeViewModel)
            R.id.itemItem -> UpdateItemDialog(itemViewModel)
            R.id.purchaseItem -> UpdatePurchaseDialog(purchaseViewModel)
            R.id.clientItem -> UpdateClientDialog(clientViewModel)
            R.id.orderItem -> UpdateOrderDialog(orderViewModel)
            else -> null
        }
        dialog?.show(this)
    }

    private fun showDeleteDialog() {
        val dialog: EntityDialog? = when (selectedMenuItem) {
            R.id.employeeItem -> DeleteEmployeeDialog(employeeViewModel)
            R.id.itemItem -> DeleteItemDialog(itemViewModel)
            R.id.purchaseItem -> DeletePurchaseDialog(purchaseViewModel)
            R.id.clientItem -> DeleteClientDialog(clientViewModel)
            R.id.orderItem -> DeleteOrderDialog(orderViewModel)
            else -> null
        }
        dialog?.show(this)
    }

    private fun showSearchDialog() {
        val dialog: EntityDialog? = when (selectedMenuItem) {
            R.id.employeeItem -> SearchEmployeeDialog(employeeViewModel)
            R.id.itemItem -> SearchItemDialog(itemViewModel)
            R.id.purchaseItem -> SearchPurchaseDialog(purchaseViewModel)
            R.id.clientItem -> SearchClientDialog(clientViewModel)
            R.id.orderItem -> SearchOrderDialog(orderViewModel)
            else -> null
        }
        dialog?.show(this)
    }
}