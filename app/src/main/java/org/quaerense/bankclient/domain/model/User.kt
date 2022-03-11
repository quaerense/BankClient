package org.quaerense.bankclient.domain.model

data class User(
    val cardNumber: String?,
    val iconId: Int,
    val cardholderName: String?,
    val valid: String?,
    val balance: String?,
    val transactionHistory: List<TransactionHistory>?
)