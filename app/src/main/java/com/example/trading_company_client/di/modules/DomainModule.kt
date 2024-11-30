package com.example.trading_company_client.di.modules

import com.example.trading_company_client.domain.repository.ClientRepository
import com.example.trading_company_client.domain.repository.EmployeeRepository
import com.example.trading_company_client.domain.repository.ItemRepository
import com.example.trading_company_client.domain.repository.LoginRequestRepository
import com.example.trading_company_client.domain.repository.OrderRepository
import com.example.trading_company_client.domain.repository.PurchaseRepository
import com.example.trading_company_client.domain.usecase.ClientUseCase
import com.example.trading_company_client.domain.usecase.EmployeeUseCase
import com.example.trading_company_client.domain.usecase.ItemUseCase
import com.example.trading_company_client.domain.usecase.LoginRequestUseCase
import com.example.trading_company_client.domain.usecase.OrderUseCase
import com.example.trading_company_client.domain.usecase.PurchaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideClientUseCase(clientRepository: ClientRepository): ClientUseCase {
        return ClientUseCase(clientRepository)
    }

    @Provides
    fun provideEmployeeUseCase(employeeRepository: EmployeeRepository): EmployeeUseCase {
        return EmployeeUseCase(employeeRepository)
    }

    @Provides
    fun provideItemUseCase(itemRepository: ItemRepository): ItemUseCase {
        return ItemUseCase(itemRepository)
    }

    @Provides
    fun provideLoginRequestUseCase(loginRequestRepository: LoginRequestRepository): LoginRequestUseCase {
        return LoginRequestUseCase(loginRequestRepository)
    }

    @Provides
    fun provideOrderUseCase(orderRepository: OrderRepository): OrderUseCase {
        return OrderUseCase(orderRepository)
    }

    @Provides
    fun providePurchaseUseCase(purchaseRepository: PurchaseRepository): PurchaseUseCase {
        return PurchaseUseCase(purchaseRepository)
    }
}