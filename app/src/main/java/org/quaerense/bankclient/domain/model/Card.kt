package org.quaerense.bankclient.domain.model

data class Card(
    val cardNumber: String?,
    val iconId: Int,
    val cardholderName: String?,
    val valid: String?,
    val balance: Double?,
    val transactionHistory: List<Transaction>?
)