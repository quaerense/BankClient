package org.quaerense.bankclient.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.quaerense.bankclient.data.dao.CurrencyDao
import org.quaerense.bankclient.data.dao.UserDao
import org.quaerense.bankclient.data.database.model.CurrencyDbModel
import org.quaerense.bankclient.data.database.model.TransactionHistoryDbModel
import org.quaerense.bankclient.data.database.model.UserDbModel

@Database(
    entities = [CurrencyDbModel::class, TransactionHistoryDbModel::class, UserDbModel::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun currencyDao(): CurrencyDao

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "bank.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            db?.let {
                return it
            }

            synchronized(LOCK) {
                db?.let {
                    return it
                }

                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance

                return instance
            }
        }
    }

}