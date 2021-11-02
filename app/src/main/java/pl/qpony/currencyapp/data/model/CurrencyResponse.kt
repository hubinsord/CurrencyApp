package pl.qpony.currencyapp.data.model

import java.math.BigDecimal


data class CurrencyResponse(
    val success: Boolean,
    val timestamp: Int,
    val base: String,
    val date: String,
    val rates: Map<String, BigDecimal>,
)