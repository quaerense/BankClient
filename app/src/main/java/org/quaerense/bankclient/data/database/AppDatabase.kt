package org.quaerense.bankclient.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.quaerense.bankclient.data.dao.CurrencyDao
import org.quaerense.bankclient.data.dao.CardDao
import org.quaerense.bankclient.data.database.model.CurrencyDbModel
import org.quaerense.bankclient.data.database.model.TransactionDbModel
import org.quaerense.bankclient.data.database.model.CardDbModel

@Database(
    entities = [CurrencyDbModel::class, TransactionDbModel::class, CardDbModel::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

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