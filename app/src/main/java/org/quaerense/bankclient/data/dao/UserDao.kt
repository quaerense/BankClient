package org.quaerense.bankclient.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.quaerense.bankclient.data.database.model.UserDbModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserDbModel)

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<UserDbModel>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun get(id: Int): LiveData<UserDbModel>
}