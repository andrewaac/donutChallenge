package com.andrewaac.donutchallenge.ui.screens.creditDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.databinding.FragmentCreditDetailsBinding
import com.andrewaac.donutchallenge.model.CreditReportInfo

class CreditDetailsFragment : Fragment(R.layout.fragment_credit_details) {

    private lateinit var binding: FragmentCreditDetailsBinding

    private val args: CreditDetailsFragmentArgs? by navArgs()

    private val creditReportInfo: CreditReportInfo?
        get() = args?.creditReportInfo?.toDomain()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditDetailsBinding.inflate(inflater, container, false)
        creditReportInfo?.let {
            binding.equifaxScoreBandValue.text = it.equifaxScoreBandDescription
            binding.equifaxScoreValue.text = "${it.equifaxScoreBand}"
        }
        return binding.root
    }
}
