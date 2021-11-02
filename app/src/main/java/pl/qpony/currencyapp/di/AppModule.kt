package pl.qpony.currencyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pl.qpony.currencyapp.Constants
import pl.qpony.currencyapp.data.api.CurrencyApi
import pl.qpony.currencyapp.data.repository.CurrencyRepositoryImpl
import pl.qpony.currencyapp.domain.CurrencyRepository
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

}