package com.andrewaac.donutchallenge.ui.screens.creditDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.andrewaac.donutchallenge.R

class CreditDetailsFragment : Fragment(R.layout.fragment_credit_details) {

    private val args: CreditDetailsFragmentArgs by navArgs()

    private val creditScore: Int
        get() = args.creditScoreValue

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view?.apply {
            val textView = findViewById<TextView>(R.id.test_text_view)
            textView.text = "You have a score of $creditScore"
        }
        return view
    }
}
