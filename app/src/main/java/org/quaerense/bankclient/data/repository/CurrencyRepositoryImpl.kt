package org.quaerense.bankclient.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
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
        workManager.enqueueUniqueWork(
            RefreshCurrencyWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshCurrencyWorker.makeRequest()
        )
    }

    override fun getCurrency(charCode: String): LiveData<Currency> {
        return Transformations.map(dao.get(charCode)) {
            mapper.mapDbModelToEntity(it)
        }
    }
}