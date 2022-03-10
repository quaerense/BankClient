package org.quaerense.bankclient.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("ID")
    @Expose
    var id: String,

    @SerializedName("NumCode")
    @Expose
    var numCode: String? = null,

    @SerializedName("CharCode")
    @Expose
    var charCode: String? = null,

    @SerializedName("Nominal")
    @Expose
    var nominal: Int? = null,

    @SerializedName("Name")
    @Expose
    var name: String? = null,

    @SerializedName("Value")
    @Expose
    var value: Double? = null,

    @SerializedName("Previous")
    @Expose
    var previous: Double? = null
)