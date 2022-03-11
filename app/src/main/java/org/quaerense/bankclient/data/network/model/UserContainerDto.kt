package org.quaerense.bankclient.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserContainerDto(
    @SerializedName("users")
    @Expose
    var users: List<UserDto>? = null
)