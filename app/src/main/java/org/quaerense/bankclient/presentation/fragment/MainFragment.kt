package org.quaerense.bankclient.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.quaerense.bankclient.R
import org.quaerense.bankclient.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvCard.setOnClickListener {
            startMyCardsFragment()
        }
    }

    private fun startMyCardsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MyCardsFragment.newInstance())
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {

        private const val CARD_NUMBER = "card number"

        fun newInstance(cardNumber: String): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putString(CARD_NUMBER, cardNumber)
                }
            }
        }
    }
}