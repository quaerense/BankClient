package org.quaerense.bankclient.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.quaerense.bankclient.data.database.model.CurrencyDbModel

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<CurrencyDbModel>)

    @Query("SELECT * FROM currency WHERE charCode = :charCode LIMIT 1")
    fun get(charCode: String): LiveData<CurrencyDbModel>
}