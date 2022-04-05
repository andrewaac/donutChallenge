package com.andrewaac.donutchallenge.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andrewaac.donutchallenge.R
import com.andrewaac.donutchallenge.ui.components.donutView.DonutView
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewClickListener
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Error
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Loaded
import com.andrewaac.donutchallenge.ui.components.donutView.DonutViewState.Loading
import com.andrewaac.donutchallenge.ui.navigationArguments.toCreditReportInfoArg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), DonutViewClickListener {

    private lateinit var donutView: DonutView

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view?.apply {
            donutView = view.findViewById(R.id.donut)
            donutView.donutViewClickListener = this@HomeFragment
        }
        setupViewModelObservers()
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCreditScore()
    }

    override fun onDonutViewClicked(donutViewState: DonutViewState) {
        when (donutViewState) {
            Error -> viewModel.getCreditScore()
            Loading -> Unit
            is Loaded -> showDetails()
        }
    }

    private fun showDetails() {
        val currentState = viewModel.stateHome.value
        if (currentState is HomeViewState.Loaded) {
            findNavController()
                .navigate(
                    HomeFragmentDirections.goToCreditDetailsFragment(
                        currentState.toCreditReportInfoArg()
                    )
                )
        }
    }

    private fun setupViewModelObservers() {
        viewModel.stateHome.observe(viewLifecycleOwner) {
            when (it) {
                HomeViewState.Loading -> donutView.updateState(Loading)
                HomeViewState.Error -> donutView.updateState(Error)
                is HomeViewState.Loaded -> {
                    val donutState = Loaded(
                        score = it.score,
                        maxScore = it.maxScore,
                        minScore = it.minScore
                    )
                    donutView.updateState(donutState)
                }
            }
        }
    }
}
