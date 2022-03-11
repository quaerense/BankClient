package org.quaerense.bankclient.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.quaerense.bankclient.R
import org.quaerense.bankclient.data.repository.UserRepositoryImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = UserRepositoryImpl(application)
        repository.loadData()
        repository.getUserList().observe(this) {
            Log.d("MainActivity", it.toString())
        }
    }
}