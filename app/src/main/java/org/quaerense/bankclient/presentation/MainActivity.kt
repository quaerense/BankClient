package org.quaerense.bankclient.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.quaerense.bankclient.R
import org.quaerense.bankclient.data.network.ApiFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            val info = ApiFactory.apiService.getCurrencyRate()
            Log.d("MainActivity", info.toString())
        }
    }
}