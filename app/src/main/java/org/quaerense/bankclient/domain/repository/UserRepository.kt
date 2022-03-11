package org.quaerense.bankclient.domain.repository

import androidx.lifecycle.LiveData
import org.quaerense.bankclient.domain.model.User

interface UserRepository {

    fun loadData()

    fun getUserList(): LiveData<List<User>>

    fun getUser(cardNumber: String): LiveData<User>
}