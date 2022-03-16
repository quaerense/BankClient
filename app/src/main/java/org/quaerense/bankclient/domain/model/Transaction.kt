package org.quaerense.bankclient.domain.model

data class Transaction(
    val id: Long,
    val title: String?,
    val iconUrl: String?,
    val date: String?,
    val amount: Double?,
    var convertedAmount: Double? = 0.0,
    val convertedAmountChar: String? = ""
)