package pl.qpony.currencyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import pl.qpony.currencyapp.Constants
import pl.qpony.currencyapp.data.api.CurrencyApi
import pl.qpony.currencyapp.data.repository.CurrencyRepositoryImpl
import pl.qpony.currencyapp.domain.CurrencyRepository
import pl.qpony.currencyapp.util.DispatcherProvider
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi(client: OkHttpClient): CurrencyApi = Retrofit.Builder()
        .baseUrl(Constants.FIXER_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideCurrencyRepository(currencyApi: CurrencyApi): CurrencyRepository = CurrencyRepositoryImpl(currencyApi)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}