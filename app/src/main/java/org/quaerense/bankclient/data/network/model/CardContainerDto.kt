package org.quaerense.bankclient.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CardContainerDto(
    @SerializedName("users")
    @Expose
    var cards: List<CardDto>? = null
)