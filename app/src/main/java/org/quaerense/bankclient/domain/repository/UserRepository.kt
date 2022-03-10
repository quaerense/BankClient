package org.quaerense.bankclient.domain.repository

import androidx.lifecycle.LiveData
import org.quaerense.bankclient.domain.model.User

interface UserRepository {

    fun getUsersList(): LiveData<List<User>>

    fun getUser(): LiveData<User>

    fun loadData()
}