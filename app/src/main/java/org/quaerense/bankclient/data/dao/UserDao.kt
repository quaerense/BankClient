package org.quaerense.bankclient.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.quaerense.bankclient.data.database.model.UserDbModel
import org.quaerense.bankclient.data.database.model.UserWithTransactionHistory

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserDbModel)

    @Transaction
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<UserWithTransactionHistory>>

    @Transaction
    @Query("SELECT * FROM user WHERE id = :id")
    fun get(id: Int): LiveData<UserWithTransactionHistory>
}