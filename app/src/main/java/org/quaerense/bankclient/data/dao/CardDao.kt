package org.quaerense.bankclient.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.quaerense.bankclient.data.database.model.CardDbModel
import org.quaerense.bankclient.data.database.model.CardWithTransactionHistory
import org.quaerense.bankclient.data.database.model.TransactionHistoryDbModel

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: CardDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionHistory(transactionHistory: List<TransactionHistoryDbModel>)

    @Transaction
    @Query("SELECT * FROM card ORDER BY cardNumber")
    fun getAll(): LiveData<List<CardWithTransactionHistory>>

    @Transaction
    @Query("SELECT * FROM card WHERE cardNumber = :cardNumber LIMIT 1")
    fun get(cardNumber: String): LiveData<CardWithTransactionHistory>

    @Query("DELETE FROM transaction_history")
    fun deleteTransactionHistory()
}