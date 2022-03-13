package org.quaerense.bankclient.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.quaerense.bankclient.data.database.model.TransactionHistoryDbModel
import org.quaerense.bankclient.data.database.model.UserDbModel
import org.quaerense.bankclient.data.database.model.UserWithTransactionHistory

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionHistory(transactionHistory: List<TransactionHistoryDbModel>)

    @Transaction
    @Query("SELECT * FROM user ORDER BY cardNumber")
    fun getAll(): LiveData<List<UserWithTransactionHistory>>

    @Transaction
    @Query("SELECT * FROM user WHERE cardNumber = :cardNumber")
    fun get(cardNumber: String): LiveData<UserWithTransactionHistory>
}