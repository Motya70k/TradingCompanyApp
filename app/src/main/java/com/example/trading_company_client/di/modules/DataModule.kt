package com.example.trading_company_client.di.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.trading_company_client.data.network.ClientProvider
import com.example.trading_company_client.data.repository.ClientRepositoryImpl
import com.example.trading_company_client.data.repository.EmployeeRepositoryImpl
import com.example.trading_company_client.data.repository.ItemRepositoryImpl
import com.example.trading_company_client.data.repository.LoginRequestRepositoryImpl
import com.example.trading_company_client.data.repository.OrderRepositoryImpl
import com.example.trading_company_client.data.repository.PurchaseRepositoryImpl
import com.example.trading_company_client.domain.repository.ClientRepository
import com.example.trading_company_client.domain.repository.EmployeeRepository
import com.example.trading_company_client.domain.repository.ItemRepository
import com.example.trading_company_client.domain.repository.LoginRequestRepository
import com.example.trading_company_client.domain.repository.OrderRepository
import com.example.trading_company_client.domain.repository.PurchaseRepository
import com.example.trading_company_client.utils.TokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideLoginRequestRepository(): LoginRequestRepository {
        return LoginRequestRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideClientRepository(clientProvider: ClientProvider): ClientRepository {
        return ClientRepositoryImpl(clientProvider)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(clientProvider: ClientProvider): EmployeeRepository {
        return EmployeeRepositoryImpl(clientProvider)
    }

    @Provides
    @Singleton
    fun provideItemRepository(clientProvider: ClientProvider): ItemRepository {
        return ItemRepositoryImpl(clientProvider)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(clientProvider: ClientProvider): OrderRepository {
        return OrderRepositoryImpl(clientProvider)
    }

    @Provides
    @Singleton
    fun providePurchaseRepository(clientProvider: ClientProvider): PurchaseRepository {
        return PurchaseRepositoryImpl(clientProvider)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("token", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideTokenStorage(sharedPreferences: SharedPreferences): TokenStorage {
        return TokenStorage(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideClientProvider(): ClientProvider {
        return ClientProvider()
    }
}