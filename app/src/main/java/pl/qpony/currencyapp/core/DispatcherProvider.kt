package pl.qpony.currencyapp.core

import kotlinx.coroutines.CoroutineDispatcher

//for future testing purposes
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

