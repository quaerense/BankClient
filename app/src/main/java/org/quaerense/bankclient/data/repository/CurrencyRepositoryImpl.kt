package org.quaerense.bankclient.data.repository

import android.app.Application
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

    override fun getCurrency(charCode: String): Currency {
        val runnable = object : Runnable {
            @Volatile
            private var currency: Currency = Currency("", "", -1.0)

            override fun run() {
                val dbModel = dao.get(charCode)
                currency = mapper.mapDbModelToEntity(dbModel)
            }

            fun getCurrency(): Currency {
                return currency
            }
        }
        val thread = Thread(runnable)
        thread.start()
        thread.join()

        return runnable.getCurrency()
    }
}