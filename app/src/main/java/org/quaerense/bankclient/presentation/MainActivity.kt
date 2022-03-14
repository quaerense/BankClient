package org.quaerense.bankclient.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.quaerense.bankclient.R
import org.quaerense.bankclient.presentation.fragment.MainFragment
import org.quaerense.bankclient.presentation.fragment.MyCardsFragment
import org.quaerense.bankclient.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProvider(this)[MainViewModel::class.java]

        val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val cardNumber = sharedPreferences.getString(CARD_NUMBER, UNDEFINED_CARD_NUMBER) ?: UNDEFINED_CARD_NUMBER

        if (cardNumber == UNDEFINED_CARD_NUMBER) {
            launchMyCardsFragment()
        } else {
            launchMainFragment()
        }

    }

    private fun launchMyCardsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MyCardsFragment.newInstance(true))
            .commit()
    }

    private fun launchMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance())
            .commit()
    }

    companion object {

        const val APP_PREFERENCES = "preferences"
        const val CARD_NUMBER = "card number"
        const val UNDEFINED_CARD_NUMBER = ""
    }
}