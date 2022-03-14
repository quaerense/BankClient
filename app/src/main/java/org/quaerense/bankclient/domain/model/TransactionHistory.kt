package org.quaerense.bankclient.domain.model

data class TransactionHistory(
    val id: Long,
    val title: String?,
    val iconUrl: String?,
    val date: String?,
    val amount: String?,
    var convertedAmount: String? = "0"
)