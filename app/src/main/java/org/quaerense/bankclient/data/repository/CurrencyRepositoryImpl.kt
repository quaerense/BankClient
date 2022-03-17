package org.quaerense.bankclient.data.repository

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import org.quaerense.bankclient.data.database.AppDatabase
import org.quaerense.bankclient.data.mapper.CurrencyMapper
import org.quaerense.bankclient.data.worker.RefreshCurrencyWorker
import org.quaerense.bankclient.domain.model.Currency
import org.quaerense.bankclient.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(private val application: Application) : CurrencyRepository {

    private val dao = AppDatabase.getInstance(application).currencyDao()
    private val mapper = CurrencyMapper()

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniquePeriodicWork(
            RefreshCurrencyWorker.NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            RefreshCurrencyWorker.makeRequest()
        )
    }

    override suspend fun getCurrency(charCode: String): Currency? {
        val dbModel = dao.get(charCode)
        if (dbModel != null) {
            return mapper.mapDbModelToEntity(dbModel)
        }

        return null
    }
}