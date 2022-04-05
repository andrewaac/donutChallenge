package com.andrewaac.donutchallenge.ui.screens.creditDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.model.CreditReportInfo

class CreditDetailsFragment : Fragment(R.layout.fragment_credit_details) {

    private val args: CreditDetailsFragmentArgs? by navArgs()

    private lateinit var equifaxScoreBandDesc: TextView
    private lateinit var equifaxScoreBand: TextView

    private val creditReportInfo: CreditReportInfo?
        get() = args?.creditReportInfo?.toDomain()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view?.apply {
            equifaxScoreBandDesc = findViewById(R.id.equifax_score_band_value)
            equifaxScoreBand = findViewById(R.id.equifax_score_value)

            creditReportInfo?.let {
                equifaxScoreBandDesc.text = it.equifaxScoreBandDescription
                equifaxScoreBand.text = "${it.equifaxScoreBand}"
            }
        }
        return view
    }
}
