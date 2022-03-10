package org.quaerense.bankclient.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDto(
    @SerializedName("card_number")
    @Expose
    var cardNumber: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("cardholder_name")
    @Expose
    var cardholderName: String? = null,

    @SerializedName("valid")
    @Expose
    var valid: String? = null,

    @SerializedName("balance")
    @Expose
    var balance: Double? = null,

    @SerializedName("transaction_history")
    @Expose
    var transactionHistory: List<TransactionHistory>? = null
)