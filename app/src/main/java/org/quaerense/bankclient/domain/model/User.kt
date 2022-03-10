package org.quaerense.bankclient.domain.model

data class User(
    val cardNumber: String?,
    val iconUrl: String?,
    val cardholderName: String?,
    val valid: String?,
    val balance: String?,
    val transactionHistory: List<TransactionHistory>?
)