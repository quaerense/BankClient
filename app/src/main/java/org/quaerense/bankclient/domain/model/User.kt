package org.quaerense.bankclient.domain.model

data class User(
    var cardNumber: String?,
    var iconUrl: String?,
    var cardholderName: String?,
    var valid: String?,
    var balance: String?,
    var transactionHistory: List<TransactionHistory>?
)