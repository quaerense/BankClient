package org.quaerense.bankclient.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyContainerDto(
    @SerializedName("Valute")
    @Expose
    val jsonObject: JsonObject
)