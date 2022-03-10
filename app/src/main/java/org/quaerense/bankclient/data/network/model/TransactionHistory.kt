package org.quaerense.bankclient.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TransactionHistory(
    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("icon_url")
    @Expose
    var iconUrl: String? = null,

    @SerializedName("date")
    @Expose
    var date: String? = null,

    @SerializedName("amount")
    @Expose
    var amount: String? = null
)